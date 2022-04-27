package com.example.whatsappgroupapi.services;

import com.example.whatsappgroupapi.models.Group;
import com.example.whatsappgroupapi.models.Message;
import com.example.whatsappgroupapi.models.PostMessageVO;
import com.example.whatsappgroupapi.models.User;
import com.example.whatsappgroupapi.repositories.GroupRepository;
import com.example.whatsappgroupapi.repositories.MessageRepository;
import com.example.whatsappgroupapi.repositories.UserRepository;
import com.example.whatsappgroupapi.utils.Utils;
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
    @Autowired
    private SenderService senderService;

    public String postMessage(PostMessageVO postMessageVO){
        Optional<User> optionalUser = userRepository.findById(postMessageVO.getFrom());

        Group group;
        if(Utils.isLong(postMessageVO.getGroupId())){
            group = groupRepository.findById(Long.valueOf(postMessageVO.getGroupId())).orElse(null);
        }else {
            group = groupRepository.findByGroupName(postMessageVO.getGroupId()).orElse(null);
        }

        if(optionalUser.isPresent() && group != null){
            User user = optionalUser.get();

            if(group.getParticipants().contains(user)){

                Message message = new Message(user,group, postMessageVO.getContent());

                senderService.send(group.getUniqueId(),postMessageVO.getContent());
                messageRepository.saveAndFlush(message);
                String answer = "Mesaje posteado correctamente en el grupo "+group.getGroupName()+".";
                log.error(answer);
                return answer;

            }else {
                String answer = "El numero de telefono "+user.getPhoneNumber()+" no es un participante del grupo "+group.getGroupName()+".";
                log.error(answer);
                return answer;
            }
        }else {
            String answer = "El usuario o el grupo al que intentas enviar el mensaje no existe.";
            log.error(answer);
            return answer;
        }
    }

    public List<Message> getAllMessageForGroupId(String groupId){
        Group group;
        if(Utils.isLong(groupId)){
            group = groupRepository.findById(Long.valueOf(groupId)).orElse(null);
        }else {
            group = groupRepository.findByGroupName(groupId).orElse(null);
        }

        if(group != null){
            List<Message> messageList = messageRepository.findAll().stream()
                    .filter(message -> message.getGroup().equals(group))
                    .collect(Collectors.toList());

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
