package com.Ratan.my_fist_Demo.DTO;

import com.Ratan.my_fist_Demo.Entity.BookEntity;

import java.util.ArrayList;
import java.util.List;

public class UserDto {
    private  Long id;
    private String username;

    private List<BookEntity> collection =new ArrayList<>();

    private String roles;

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public List<BookEntity> getCollection() {
        return collection;
    }

    public void setCollection(List<BookEntity> collection) {
        this.collection = collection;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
