package com.Ratan.my_fist_Demo.Service.impl;


import com.Ratan.my_fist_Demo.DTO.SigupDto;
import com.Ratan.my_fist_Demo.DTO.UserDto;
import com.Ratan.my_fist_Demo.Entity.BookEntity;
import com.Ratan.my_fist_Demo.Entity.UserEntity;
import com.Ratan.my_fist_Demo.Repository.UserRepository;
import com.Ratan.my_fist_Demo.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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


    private  static  final Logger logger=  LoggerFactory.getLogger(UserServiceImpl.class);



    @Override
    public UserDto createUser(SigupDto sigupDto){
       try {
           UserEntity newUser = new UserEntity();
           newUser.setUsername(sigupDto.getUsername());
           newUser.setPassword(passwordEncoder.encode(sigupDto.getPassword()));
           newUser.setRoles("User");
           UserEntity saved = userRepository.save(newUser);

           UserDto newUserDto = new UserDto();
           newUserDto.setId(saved.getId());
           newUserDto.setUsername(saved.getUsername());
           newUserDto.setRoles(saved.getRoles());
           return newUserDto;
       } catch (Exception e) {
           logger.info("hayfddsfdsafsdfdasfdasfdsf");
           throw new RuntimeException(e);
       }



    }

    @Override
    public List<UserDto> getAllUsers() {
       try {
           List<UserEntity> users = userRepository.findAll();
           List<UserDto> userdtos = new ArrayList<>();
           for (UserEntity user : users) {
               UserDto userdto = new UserDto();
               userdto.setId(user.getId());
               userdto.setUsername(user.getUsername());
               userdto.setRoles(user.getRoles());
               List<BookEntity> books = new ArrayList<>();
               for (BookEntity book : user.getCollection()) {
                   books.add(book);
               }
               userdto.setCollection(books);
               userdtos.add(userdto);
           }
           return userdtos;
       } catch (Exception e) {

           throw new RuntimeException(e);
       }


    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<UserEntity> userOption=userRepository.findById(id);
        if(userOption.isPresent()){
            UserEntity user=userOption.get();
            UserDto userdto=new UserDto();
            userdto.setId(user.getId());
            userdto.setUsername(user.getUsername());
            userdto.setRoles(user.getRoles());
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
            response.setRoles(user.getRoles());
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

   public UserEntity findByUsername(String username){
        return userRepository.findByUsername(username);
   }

    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }


}


