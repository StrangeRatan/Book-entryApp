package com.Ratan.my_fist_Demo.Repository;

import com.Ratan.my_fist_Demo.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

}
