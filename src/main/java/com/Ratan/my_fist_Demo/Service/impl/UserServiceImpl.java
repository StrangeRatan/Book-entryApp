package com.Ratan.my_fist_Demo.Service.impl;


import com.Ratan.my_fist_Demo.DTO.Userdto;
import com.Ratan.my_fist_Demo.Repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@Service  // This annotations   indicated this class is service class
public class UserServicImpl {

    private final UserRepository userRepository;

    public UserService (UserRepository userRepository){
        this.userRepository=userRepository;

    }

    @Override
    public User createUser(Userdto userdto){
        User user= new User();
        user.setUsername(userdto.getUsername());
        user.setPassword(userdto.getPassword());
    }
}
