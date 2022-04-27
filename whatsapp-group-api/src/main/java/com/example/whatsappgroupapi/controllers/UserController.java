package com.example.whatsappgroupapi.controllers;

import com.example.whatsappgroupapi.models.User;
import com.example.whatsappgroupapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/{phoneNumber}")
    public String registerNewUser(@PathVariable Long phoneNumber){
        return userService.registerNewUser(phoneNumber);
    }

    //FOR INTERNAL USE
    @GetMapping("")
    public Set<User> getAllUsers(){
        return userService.getAllUsers();
    }

}
