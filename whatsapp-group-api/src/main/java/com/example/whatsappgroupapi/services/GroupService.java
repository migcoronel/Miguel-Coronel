package com.example.whatsappgroupapi.services;

import com.example.whatsappgroupapi.models.Group;
import com.example.whatsappgroupapi.models.User;
import com.example.whatsappgroupapi.repositories.GroupRepository;
import com.example.whatsappgroupapi.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    public void createNewGroup(List<Long> participants, String name){
        List<User> registeredUsers = userRepository.findAllById(participants);

        if(registeredUsers.size() != participants.size()){
            log.info("Algunos de los usuarios que intentas agregar, no estan registrados en Whatsapp.");
        }

        Group group = new Group(registeredUsers,name);
        groupRepository.saveAndFlush(group);
        log.info("Nuevo grupo creado, con el nombre: '"+name+"' y los siguientes integrantes: "+ Arrays.toString(registeredUsers.toArray()));
    }

    public void updateParticipants(Long id , List<Long> newParticipants){
        Optional<Group> optional = groupRepository.findById(id);
        if(optional.isPresent()){
            Group group = optional.get();
            List<User> registeredUsers = userRepository.findAllById(newParticipants);
            if(registeredUsers.size() != newParticipants.size()){
                log.info("Algunos de los usuarios que intentas agregar, no estan registrados en Whatsapp.");
            }
            group.setParticipants(registeredUsers);
            groupRepository.saveAndFlush(group);
        }else {
            log.error("El grupo que intentas modificar, no existe.");
        }

    }

    public List<Group> findAllforParticipant(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            List<Group> groupList= groupRepository.findAll().stream()
                    .filter(group -> group.getParticipants().contains(user))
                    .collect(Collectors.toList());

            if(groupList.size()>0){
                log.info("Se encontraron "+groupList.size()+" grupos que contienen este numero de telefono.");
                return groupList;
            }else{
                log.error("No se encontro ningun grupo con este numero de telefono como participante.");
                return null;
            }

        }else {
            log.error("No se encontro ningun usuario con ese numero de telefono.");
            return null;
        }
    }
}
