package com.example.restcontroller;

import com.example.dto.UserErrorResponse;
import com.example.entity.Users;
import com.example.exception.UserNotFoundException;
import com.example.service.UserService;
import jakarta.annotation.PostConstruct;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class UserController {
    UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    List<Users> getAllUsers(){
        return userService.findAll();
    }
    @GetMapping("/users/{id}")
    Users getUserById(@PathVariable int id)
    {
        return userService.findById(id);
    }
    @PostMapping("/users")
    public Users createUser(@RequestBody Users users)
    {
        return userService.save(users);
    }
    @PatchMapping("/users/{id}")
    public Users updateUsers(@RequestBody Users users,@PathVariable int id)
    {
        return userService.update(id, users);
    }
    @DeleteMapping("/users/{id}")
    public String deleteById(@PathVariable int id){
        userService.deleteById(id);
        return "User with id:"+id+" is deleted";
    }
    @GetMapping("/users/findAllByIds")
    public List<Users> getUsersByIds(@RequestParam List<Integer> ids) {
        return userService.findAllById(ids);
    }
    @DeleteMapping("/users/deleteByIds")
    public String deleteUsersByIds(@RequestParam List<Integer> ids)
    {
         userService.deleteAllByIds(ids);
         return "Users with Ids:" + ids+" are deleted";
    }


}
