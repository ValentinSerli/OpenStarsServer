package com.serli.controller;

import com.serli.data.Coordonnees;
import com.serli.repo.CoordonneeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Base64;

@Controller
public class WebSocketController {

    private final SimpMessagingTemplate template;

    @Autowired
    public CoordonneeRepo coordonneeRepo;

    @Autowired
    WebSocketController(SimpMessagingTemplate template){
        this.template = template;
    }

    @MessageMapping("/move")
    @SendTo("/telescope/move")
    public String onMessageReceive(String message)
    {
        this.template.convertAndSend("/telescope/move", message);
        return message;
    }

    @MessageMapping("/picture")
    @SendTo("/telescope/picture")
    public Coordonnees onAskPictures(String message)
    {
//        this.template.convertAndSend("/telescope", message);
        Coordonnees planete = coordonneeRepo.findByNomPlanete(message);
        return planete;
    }

    @MessageMapping("/receive")
    @SendTo("/telescope/receive")
    public Integer onReceivePicture(Object image)
    {
//        this.template.convertAndSend("/telescope/receive", image);
        Integer s = new Integer(((byte[]) image)[0]);
        return s;
//        return new String("test");
    }
}
