package com.serli.data;

import javax.persistence.*;

@Entity
@Table(name = "rasp")
public class Rasp {

    @Id
    private String raspId;

    public String getRaspId() {
        return raspId;
    }

    public void setRaspId(String raspId) {
        this.raspId = raspId;
    }
}
