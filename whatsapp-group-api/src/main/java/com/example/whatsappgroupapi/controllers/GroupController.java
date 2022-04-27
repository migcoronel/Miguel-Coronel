package com.example.whatsappgroupapi.controllers;

import com.example.whatsappgroupapi.models.Group;
import com.example.whatsappgroupapi.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @PostMapping("/create/{groupName}")
    public void createNewGroup(@PathVariable String groupName, @RequestBody List<Long> participants){
        groupService.createNewGroup(participants,groupName);
    }

    @PutMapping("/update/{id}")
    public void updateParticipants(@PathVariable Long id, @RequestBody  List<Long> participants){
        groupService.updateParticipants(id,participants);
    }

    @GetMapping("/{userId}")
    public List<Group> getGroupsFromUserId(@PathVariable Long userId){
        return groupService.findAllforParticipant(userId);
    }
}
