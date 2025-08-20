package com.Ratan.my_fist_Demo.Contoller;

import com.Ratan.my_fist_Demo.DTO.SigupDto;
import com.Ratan.my_fist_Demo.DTO.UserDto;
import com.Ratan.my_fist_Demo.Service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<?> getAllUsers(){

        List<UserDto> all=userService.getAllUsers();
        if(all !=null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @PostMapping
//    public ResponseEntity<?> createUser(@RequestBody SigupDto newUser){
//        UserDto user = userService.createUser(newUser);
//        return new ResponseEntity<>(user,HttpStatus.CREATED);
//
//    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUsersBy(@PathVariable Long id){
        UserDto userById = userService.getUserById(id);
        if(userById !=null){
        return new ResponseEntity<>(userById,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable Long id, @RequestBody SigupDto sigupDto){
        UserDto userDto = userService.updateUserbyId(id, sigupDto);
        if(userDto.getId() !=null){
            return new ResponseEntity<>(userDto,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        UserDto userById = userService.getUserById(id);
        if(userById != null){
            userService.deleteByUserId(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
