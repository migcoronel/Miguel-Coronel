package com.example.whatsappgroupapi.services;

import com.example.whatsappgroupapi.models.Group;
import com.example.whatsappgroupapi.models.Message;
import com.example.whatsappgroupapi.models.User;
import com.example.whatsappgroupapi.repositories.GroupRepository;
import com.example.whatsappgroupapi.repositories.MessageRepository;
import com.example.whatsappgroupapi.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;

    public void sendMessage(Long fromId, Long groupId , String content){
        Optional<User> optionalUser = userRepository.findById(fromId);
        Optional<Group> optionalGroup = groupRepository.findById(groupId);

        if(optionalUser.isPresent() && optionalGroup.isPresent()){
            User user = optionalUser.get();
            Group group = optionalGroup.get();

            Message message = new Message(user,group,content);
            messageRepository.saveAndFlush(message);
            log.error("Mesaje posteado correctamente.");
        }else {
            log.error("El usuario o el grupo al que intentas enviar el mensaje no existe.");
        }
    }

    public List<Message> getAllMessageForGroupId(Long groupId){
        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        if(optionalGroup.isPresent()){
            List<Message> messageList = messageRepository.findAll().stream().filter(message -> message.getGroup().equals(optionalGroup.get())).collect(Collectors.toList());
            if(messageList.size()>0){
                log.info("Se encontraron "+messageList.size()+" mensajes para este grupo.");
                return messageList;
            }else {
                log.error("No se encontraron mensajes para este grupo.");
                return null;
            }
        }else {
            log.error("No se encontro el grupo para el ID indicado.");
            return null;
        }
    }
}
