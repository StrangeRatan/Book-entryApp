package com.Ratan.my_fist_Demo.Contoller;

import com.Ratan.my_fist_Demo.DTO.SigupDto;
import com.Ratan.my_fist_Demo.DTO.UserDto;
import com.Ratan.my_fist_Demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping
    public UserDto createUser(@RequestBody SigupDto newUser){
        return userService.createUser(newUser);
    }

    @GetMapping("/{id}")
    public UserDto getUsersBy(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public UserDto updateUserById(@PathVariable Long id, @RequestBody SigupDto sigupDto){
        return userService.updateUserbyId(id,sigupDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteByUserId(id);
        return ResponseEntity.ok("OK");


    }

}
