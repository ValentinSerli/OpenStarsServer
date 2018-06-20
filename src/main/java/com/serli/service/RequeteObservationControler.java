package com.serli.service;

import com.serli.controller.WebSocketController;
import com.serli.data.Coordonnees;
import com.serli.data.Etat;
import com.serli.data.Image;
import com.serli.repo.CoordonneeRepo;
import com.serli.repo.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://192.168.86.105:3000")
@RequestMapping("/obs")
public class RequeteObservationControler {

    @Autowired
    public CoordonneeRepo coordonneeRepo;

    @Autowired
    public ImageRepo imageRepo;

    @Autowired
    public WebSocketController webSocketController;

    @RequestMapping(method = RequestMethod.GET, value = "/check")
    public List<Coordonnees> checkRequest ()
    {
        List<Coordonnees> pasTraitee = coordonneeRepo.findByEtat(Etat.PASTRAITEE);
        List<Coordonnees> enCours = coordonneeRepo.findByEtat(Etat.ACCEPTEE);
        enCours.addAll(pasTraitee);
        return enCours;
    }



    @RequestMapping(method = RequestMethod.GET, value = "/image/{idImage}")
    public String getImage(@PathVariable("idImage") Integer id)
    {
        Image one = imageRepo.findOne(id);
        return one.getImageBase64();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/check/{id}")
    public Coordonnees updateEtat(@PathVariable("id") Integer id)
    {
        Coordonnees coord = coordonneeRepo.findOne(id);
        Image imagePlanete = imageRepo.findFirstByNomPlanete(coord.getNomPlanete());
        coord.setEtat(Etat.TRAITEE);

        coordonneeRepo.save(coord);

        return coord;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/accept/{id}")
    public Coordonnees accept(@PathVariable("id") Integer id)
    {
        Coordonnees coord = coordonneeRepo.findOne(id);
        Image imagePlanete = imageRepo.findFirstByNomPlanete(coord.getNomPlanete());
        coord.setEtat(Etat.ACCEPTEE);

        coordonneeRepo.save(coord);

        webSocketController.onMessageReceive(coord.getNomPlanete());

        return coord;
    }

}
