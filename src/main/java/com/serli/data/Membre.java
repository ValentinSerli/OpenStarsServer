package com.serli.data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Membre")
public class Membre {

    @Id
    @Column(name = "Id_membre")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMembre;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "pseudo")
    private String pseudo;

    @Column(name = "Email")
    private String mail;

    @Column(name = "motdepasse")
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name="rasp_user",
            joinColumns = @JoinColumn(name="rasp_id_membre"),
            inverseJoinColumns = @JoinColumn(name = "Id_Rasp"))
    private List<Rasp> rasps;

    @Column(name = "Pays")
    private String pays;

    @Column(name = "biographie")
    private String biographie;

    @Column(name = "questionsecrete")
    private String questionSecret;

    @Column(name = "reponsesecret")
    private String reponse;

    public Integer getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(Integer idMembre) {
        this.idMembre = idMembre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Rasp> getRasps() {
        return rasps;
    }

    public void setRasps(List<Rasp> rasps) {
        this.rasps = rasps;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getBiographie() {
        return biographie;
    }

    public void setBiographie(String biographie) {
        this.biographie = biographie;
    }

    public String getQuestionSecret() {
        return questionSecret;
    }

    public void setQuestionSecret(String questionSecret) {
        this.questionSecret = questionSecret;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }
}
