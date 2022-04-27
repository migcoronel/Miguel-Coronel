package com.example.whatsappgroupapi.services;

import com.example.whatsappgroupapi.models.User;
import com.example.whatsappgroupapi.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User registerNewUser(Long phoneNumber){
        if(userRepository.existsById(phoneNumber)){
            log.info("Ya existe un usuario registrado con el numero de telefono: "+phoneNumber+".");
            return null;
        }else {

            User user = new User(phoneNumber);
            userRepository.saveAndFlush(user);

            log.info("Nuevo usuario registrado con el numero de telefono: "+phoneNumber+".");
            return user;
        }

    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
