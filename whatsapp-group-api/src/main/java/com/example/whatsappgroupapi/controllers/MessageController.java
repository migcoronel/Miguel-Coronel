package com.example.whatsappgroupapi.controllers;

import com.example.whatsappgroupapi.models.Message;
import com.example.whatsappgroupapi.models.PostMessageVO;
import com.example.whatsappgroupapi.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping("/post")
    public void postMessage(@RequestBody PostMessageVO postMessageVO){
        messageService.sendMessage(
                postMessageVO.getFrom(),
                postMessageVO.getGroup(),
                postMessageVO.getContent()
        );
    }

    @GetMapping("/group/{groupId}")
    public List<Message> getMessages(@PathVariable Long groupId){
        return messageService.getAllMessageForGroupId(groupId);
    }
}
