package com.serli.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rasp_user")
public class RaspUser {

    @Id
    @Column(name = "rasp_id_membre")
    private Integer raspIdMembre;

    @Column(name = "Id_Rasp")
    private String idRasp;

    public Integer getRaspIdMembre() {
        return raspIdMembre;
    }

    public void setRaspIdMembre(Integer raspIdMembre) {
        this.raspIdMembre = raspIdMembre;
    }

    public String getIdRasp() {
        return idRasp;
    }

    public void setIdRasp(String idRasp) {
        this.idRasp = idRasp;
    }
}
