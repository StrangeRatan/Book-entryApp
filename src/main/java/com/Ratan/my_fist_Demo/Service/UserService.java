package com.Ratan.my_fist_Demo.Service;

import com.Ratan.my_fist_Demo.DTO.Userdto;
import com.Ratan.my_fist_Demo.Repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    Userdto createUser(Userdto userdto);
    List<Userdto> getAllUsers();
//    User getUserBy(Long id);
//    User updateUser(Long id, Userdto userdto);
//    void deleteUser(Long id);

}
