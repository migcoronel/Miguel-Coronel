package com.example.whatsappgroupapi.services;

import com.example.whatsappgroupapi.models.User;
import com.example.whatsappgroupapi.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public String registerNewUser(Long phoneNumber){
        if(userRepository.existsById(phoneNumber)){

            String answer = "Ya existe un usuario registrado con el numero de telefono: " + phoneNumber + ".";
            log.info(answer);
            return answer;

        }else {

            User user = new User(phoneNumber);
            userRepository.saveAndFlush(user);

            String answer = "Nuevo usuario registrado con el numero de telefono: " + phoneNumber + ".";
            log.info(answer);
            return answer;
        }

    }

    public Set<User> getAllUsers(){
        return new HashSet<>(userRepository.findAll());
    }
}
