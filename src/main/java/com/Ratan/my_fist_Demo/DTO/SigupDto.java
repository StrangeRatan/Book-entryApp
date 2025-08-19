package com.Ratan.my_fist_Demo.DTO;

import com.Ratan.my_fist_Demo.Entity.BookEntity;

import java.util.ArrayList;
import java.util.List;

public class SigupDto {
    private String username;
    private String password;
    private List<BookEntity> collection =new ArrayList<>();

    public List<BookEntity> getCollection() {
        return collection;
    }

    public void setCollection(List<BookEntity> collection) {
        this.collection = collection;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
