package com.Ratan.my_fist_Demo.Contoller;

import com.Ratan.my_fist_Demo.ApiResponse.weatherpojo;
import com.Ratan.my_fist_Demo.DTO.SigupDto;
import com.Ratan.my_fist_Demo.DTO.UserDto;
import com.Ratan.my_fist_Demo.Entity.UserEntity;
import com.Ratan.my_fist_Demo.Repository.UserRepository;
import com.Ratan.my_fist_Demo.Service.UserService;
import com.Ratan.my_fist_Demo.Service.WeathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeathService weathService;

    @GetMapping("/getProfile")
    public ResponseEntity<?> getUsersBy(Authentication authentication){
        String name = authentication.getName();
        UserEntity user=userRepository.findByUsername(name);
        if(user !=null){
        return new ResponseEntity<>(user,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/update-Profile/{byId}")
    public ResponseEntity<?> updateUserById(@PathVariable Long byId, @RequestBody SigupDto sigupDto){
        UserDto userDto = userService.updateUserbyId(byId, sigupDto);
        if(userDto.getId() !=null){
            return new ResponseEntity<>(userDto,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete-Profile/{byId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long byId){
        UserDto userById = userService.getUserById(byId);
        if(userById != null){
            userService.deleteByUserId(byId);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        weatherpojo weatherResponse = weathService.getWeather("Varanasi");
        String greeting = "";
        if(weatherResponse !=null){
            greeting = ", Weather feels like " + weatherResponse.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("Hi " + authentication.getName() + greeting, HttpStatus.OK);
    }

}
