package com.Ratan.my_fist_Demo.Service.impl;


import com.Ratan.my_fist_Demo.DTO.Userdto;
import com.Ratan.my_fist_Demo.Entity.UserEntity;
import com.Ratan.my_fist_Demo.Repository.UserRepository;
import com.Ratan.my_fist_Demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service  // This annotations   indicated this class is service class
public class UserServiceImpl implements UserService {

    @Autowired
    private  UserRepository userRepository;



    @Override
    public Userdto createUser(Userdto userdto){
        UserEntity userEntity= new UserEntity();
        userEntity.setUsername(userdto.getUsername());
        userEntity.setPassword(userdto.getPassword());

        UserEntity savedUserEntity=userRepository.save(userEntity);

        Userdto response=new Userdto();
        response.setId(savedUserEntity.getId());
        response.setUsername(savedUserEntity.getUsername());
        response.setPassword(savedUserEntity.getPassword());
        return response;
    }

    @Override
    public List<Userdto> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(user ->{
                                 Userdto dto=new Userdto();
                                 dto.setId(user.getId());
                                 dto.setUsername(user.getUsername());
                                 dto.setPassword(user.getPassword());
                                 return dto;
                })
                        .collect(Collectors.toList());

    }
}


