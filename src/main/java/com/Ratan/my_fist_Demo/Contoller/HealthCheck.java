package com.Ratan.my_fist_Demo.Contoller;

import com.Ratan.my_fist_Demo.DTO.SigupDto;
import com.Ratan.my_fist_Demo.DTO.UserDto;
import com.Ratan.my_fist_Demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController  //Marks class as REST controller (returns data, not view)
@RequestMapping("/public")  //Base path for all endpoints inside this class
public class HealthCheck {

    @Autowired
    private UserService userService;

    @GetMapping("/HealthCheck")  // Handles GET request for "/health"
    public String checkHeathstautus(){
        return "OK";
    }
    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody SigupDto newUser){
        UserDto user = userService.createUser(newUser);
        return new ResponseEntity<>(user, HttpStatus.CREATED);

    }

}
