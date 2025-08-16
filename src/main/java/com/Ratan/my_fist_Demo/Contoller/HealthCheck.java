package com.Ratan.my_fist_Demo.Contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  //Marks class as REST controller (returns data, not view)
@RequestMapping("/health")  //Base path for all endpoints inside this class
public class HealthCheck {

    @GetMapping  // Handles GET request for "/health"
    public String checkHeathstautus(){
        return "OK";
    }
}
