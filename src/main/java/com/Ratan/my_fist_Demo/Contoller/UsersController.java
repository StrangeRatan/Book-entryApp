package com.Ratan.my_fist_Demo.Contoller;

import com.Ratan.my_fist_Demo.DTO.Userdto;
import com.Ratan.my_fist_Demo.Entity.UserEntity;
import com.Ratan.my_fist_Demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Userdto> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping
    public Userdto createUser(@RequestBody Userdto newUser){
        return userService.createUser(newUser);
    }

}
