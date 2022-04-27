package com.example.whatsappgroupapi.controllers;

import com.example.whatsappgroupapi.models.Message;
import com.example.whatsappgroupapi.models.PostMessageVO;
import com.example.whatsappgroupapi.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping("/post")
    public String postMessage(@RequestBody PostMessageVO postMessageVO){
         return messageService.postMessage(postMessageVO);
    }

    //For Internal Use
    @GetMapping("/group/{groupId}")
    public List<Message> getMessages(@PathVariable String groupId){
        return messageService.getAllMessageForGroupId(groupId);
    }
}
