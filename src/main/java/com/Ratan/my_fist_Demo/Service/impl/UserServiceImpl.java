package com.Ratan.my_fist_Demo.Service.impl;


import com.Ratan.my_fist_Demo.DTO.SigupDto;
import com.Ratan.my_fist_Demo.DTO.UserDto;
import com.Ratan.my_fist_Demo.Entity.UserEntity;
import com.Ratan.my_fist_Demo.Repository.UserRepository;
import com.Ratan.my_fist_Demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service  // This annotations   indicated this class is service class
public class UserServiceImpl implements UserService {

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public UserDto createUser(SigupDto sigupDto){
        UserEntity newUser=new UserEntity();
        newUser.setUsername(sigupDto.getUsername());
        newUser.setPassword(passwordEncoder.encode(sigupDto.getPassword()));
        UserEntity saved = userRepository.save(newUser);

        UserDto newUserDto=new UserDto();
        newUserDto.setId(saved.getId());
        newUserDto.setUsername(saved.getUsername());
        return newUserDto;

    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserEntity> users=userRepository.findAll();
        List<UserDto> userdtos=new ArrayList<>();
        for(UserEntity user : users){
            UserDto userdto=new UserDto();
            userdto.setId(user.getId());
            userdto.setUsername(user.getUsername());
            userdtos.add(userdto);
        }
        return userdtos;


    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<UserEntity> userOption=userRepository.findById(id);
        if(userOption.isPresent()){
            UserEntity user=userOption.get();
            UserDto userdto=new UserDto();
            userdto.setId(user.getId());
            userdto.setUsername(user.getUsername());
            return userdto;
        }
        return null;
    }

    @Override
    public UserDto updateUserbyId(Long id, SigupDto sigupDto) {
        Optional<UserEntity> userOption = userRepository.findById(id);
        if(userOption.isPresent()){
            UserEntity user=userOption.get();
            user.setUsername(sigupDto.getUsername());
            if(sigupDto.getPassword() !=null && !sigupDto.getPassword().isEmpty()){
                user.setPassword(passwordEncoder.encode(sigupDto.getPassword()));
            }
             userRepository.save(user);

            UserDto response=new UserDto();
            response.setId(user.getId());
            response.setUsername(user.getUsername());
            return response;
        }
        else {
           return null;
        }

    }

    public void deleteByUserId(Long id) {
        if(!userRepository.existsById(id)){
        throw new RuntimeException("User not found"+ id);
    }
        userRepository.deleteById(id);
   }
}


