package com.serli.telescope.fx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan({"com.serli"})
@EntityScan("com.serli.data")
@EnableJpaRepositories("com.serli.repo")
public class Application {



    public Application(){


    }

//    @RequestMapping(method = RequestMethod.GET, value = "/home")
//    public void home(){
//
//    }
    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    public static void main (String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
