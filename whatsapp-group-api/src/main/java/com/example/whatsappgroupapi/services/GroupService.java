package com.example.whatsappgroupapi.services;

import com.example.whatsappgroupapi.models.Group;
import com.example.whatsappgroupapi.models.GroupVO;
import com.example.whatsappgroupapi.models.User;
import com.example.whatsappgroupapi.repositories.GroupRepository;
import com.example.whatsappgroupapi.repositories.UserRepository;
import com.example.whatsappgroupapi.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    public String createNewGroup(String groupName , GroupVO groupVO){
        Optional<User> optionalFrom = userRepository.findById(groupVO.getFrom());

        if(optionalFrom.isEmpty()){
            String answer = "Quien inicio el pedido, no es usuario registrado de Whatsapp";
            log.error(answer);
            return answer;
        }
        User admin = optionalFrom.get();

        //Setting Admins
        Set<User> registeredAdminUsers = new HashSet<>(userRepository.findAllById(groupVO.getAdmins()));
        if(registeredAdminUsers.size() != groupVO.getAdmins().size()){
            log.info("Algunos de los Admins que intentas agregar, no estan registrados en Whatsapp.");
        }
        registeredAdminUsers.add(admin);

        //Setting Participants
        Set<User> registeredUsers = new HashSet<>(userRepository.findAllById(groupVO.getParticipants()));
        if(registeredUsers.size() != groupVO.getParticipants().size()){
            log.info("Algunos de los usuarios que intentas agregar, no estan registrados en Whatsapp.");
        }
        registeredUsers.addAll(registeredAdminUsers);
        registeredUsers.add(admin);


        //Saving Group
        Group group = new Group(registeredAdminUsers,registeredUsers,groupName);
        groupRepository.saveAndFlush(group);
        String answer = "El grupo "+group.getGroupName()+" ha sido creado con exito.";
        log.info(answer);
        return answer;
    }

    public String updateGroup(String groupId , GroupVO groupVO){
        Group group;
        if(Utils.isLong(groupId)){
            group = groupRepository.findById(Long.valueOf(groupId)).orElse(null);
        }else {
            group = groupRepository.findByGroupName(groupId).orElse(null);
        }

        if(group != null){
            Optional<User> optionalFrom = userRepository.findById(groupVO.getFrom());

            if(optionalFrom.isEmpty()){
                String answer = "Quien inicio el pedido, no es usuario registrado de Whatsapp";
                log.error(answer);
                return answer;
            }

            User from = optionalFrom.get();
            if(group.getAdmins().contains(from)){

                //Setting Participants
                Set<User> registeredUsers = new HashSet<>(userRepository.findAllById(groupVO.getParticipants()));
                if(registeredUsers.size() != groupVO.getParticipants().size()){
                    log.info("Algunos de los usuarios que intentas agregar, no estan registrados en Whatsapp.");
                }
                group.setParticipants(registeredUsers);

                //Setting Admins
                Set<User> registeredAdminUsers = new HashSet<>(userRepository.findAllById(groupVO.getAdmins()));
                if(registeredAdminUsers.size() != groupVO.getAdmins().size()){
                    log.info("Algunos de los Admins que intentas agregar, no estan registrados en Whatsapp.");
                }
                group.setAdmins(registeredAdminUsers);

                //Saving Group
                groupRepository.saveAndFlush(group);
                String answer = "El grupo "+group.getGroupName()+" ha sido actualizado con exito.";
                log.info(answer);
                return answer;
            }

        }else {
            String answer = "El grupo que intentas modificar, no existe.";
            log.error(answer);
            return answer;
        }

        return null;
    }

    public List<Group> findAllforParticipant(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            List<Group> groupSet= groupRepository.findAll().stream()
                    .filter(group -> group.getParticipants().contains(user))
                    .collect(Collectors.toList());

            if(groupSet.size()>0){
                log.info("Se encontraron "+groupSet.size()+" grupo(s) que contienen este numero de telefono.");
                return groupSet;
            }else{
                log.error("No se encontro ningun grupo con este numero de telefono como participante.");
                return null;
            }

        }else {
            log.error("No se encontro ningun usuario con ese numero de telefono.");
            return null;
        }
    }

    public Group findGroup(String groupId) {
        Group group;
        if(Utils.isLong(groupId)){
            group = groupRepository.findById(Long.valueOf(groupId)).orElse(null);
        }else {
            group = groupRepository.findByGroupName(groupId).orElse(null);
        }

        if(group!= null){
                log.info("Se encontro el grupo "+group.getGroupName()+" con "+group.getParticipants().size()+" participante(s).");
                return group;

        }else {
            log.error("No se encontro ningun grupo con ese nombre o ID.");
            return null;
        }
    }
}
