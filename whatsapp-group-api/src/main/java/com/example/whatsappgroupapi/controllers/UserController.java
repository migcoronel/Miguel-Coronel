package com.example.whatsappgroupapi.controllers;

import com.example.whatsappgroupapi.models.User;
import com.example.whatsappgroupapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/{phoneNumber}")
    public void registerNewUser(@PathVariable Long phoneNumber){
        userService.registerNewUser(phoneNumber);
    }

    @GetMapping("")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

}
