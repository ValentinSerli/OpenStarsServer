package com.serli.service;

import com.serli.data.Membre;
import com.serli.data.Rasp;
import com.serli.data.RaspUser;
import com.serli.repo.MembreRepo;
import com.serli.repo.RaspRepo;
import com.serli.repo.RaspUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.serli.data.Password.byteToHex;

@RestController
@CrossOrigin(origins = "http://192.168.86.105:8082")
@RequestMapping("/identification")
public class InscriptionController {

    @Autowired
    public MembreRepo membreRepo;

    @Autowired
    public RaspRepo raspRepo;

    @Autowired
    public RaspUserRepo raspUserRepo;

    @RequestMapping(method = RequestMethod.POST, value = "/check", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String checkInscrit(@RequestBody Membre user) {
        UUID id = UUID.randomUUID();
        System.out.println(user.getMail());
        System.out.println(user.getPassword());
        Rasp rasp = new Rasp();
        rasp.setRaspId(id.toString());
        List<String> listeId = new ArrayList<String>();
        listeId.add(id.toString());

        String sha1 = user.getPassword();
        MessageDigest crypt;
        try {
            crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(user.getPassword().getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
            System.out.println(sha1);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println("adresse mail " + user.getMail());
        System.out.println("Mot de passe crypté : " + sha1);

//        Membre membre = membreRepo.findByMail(user.getMail());
        Membre membre = membreRepo.findByPasswordAndMail(sha1, user.getMail());

        if (membre == null) {
            return "Erreur lors de la saisie de l'identifiant ou du mot de passe";
        }

        if (membre.getRasps() == null) {
            membre.setRasps(new ArrayList<>());
        }

        RaspUser raspUser = raspUserRepo.findByRaspIdMembre(membre.getIdMembre());

        if (raspUser != null) {
            System.out.println("L'utilisateur existe possède déjà un ID rasp");
            return "ID déjà saisie";
        } else {
            System.out.println("Génération d'un ID pour ce nouvelle utilisateur");
            rasp = raspRepo.save(rasp);
            membre.getRasps().add(rasp);
            membreRepo.save(membre);
            return id.toString();
        }
    }
}
