package com.example.service;

import com.example.entity.Users;

import java.util.List;

public interface UserService {
    public List<Users> findAll();
    public Users findById(int id);
    public Users save(Users users);
    public Users update(int id,Users users);
    public  void deleteById(int id);

}
