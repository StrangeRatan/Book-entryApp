package com.Ratan.my_fist_Demo.Service;

import com.Ratan.my_fist_Demo.DTO.SigupDto;
import com.Ratan.my_fist_Demo.DTO.UserDto;
import com.Ratan.my_fist_Demo.Entity.UserEntity;

import java.util.List;


public interface UserService {
    UserDto createUser(SigupDto sigupDto);
    List<UserDto> getAllUsers();
    UserDto getUserById(Long id);
    UserDto updateUserbyId(Long id, SigupDto sigupDto);
      void deleteByUserId(Long id);
      UserEntity findByUsername(String username);
    UserEntity save(UserEntity user) ;


}
