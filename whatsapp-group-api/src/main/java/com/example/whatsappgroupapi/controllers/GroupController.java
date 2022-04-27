package com.example.whatsappgroupapi.controllers;

import com.example.whatsappgroupapi.models.Group;
import com.example.whatsappgroupapi.models.GroupVO;
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
    public String createNewGroup(@PathVariable String groupName, @RequestBody GroupVO groupVO){
        return groupService.createNewGroup(groupName,groupVO);
    }

    @PutMapping("/update/{groupId}")
    public String updateParticipants(@PathVariable String groupId, @RequestBody GroupVO groupVO){
        return groupService.updateGroup(groupId, groupVO);
    }

    @GetMapping("/{groupId}")
    public Group getGroupFromNameOrId(@PathVariable String groupId){
        return groupService.findGroup(groupId);
    }

    @GetMapping("/findByUser/{userId}")
    public List<Group> getGroupsFromUserId(@PathVariable Long userId){
        return groupService.findAllforParticipant(userId);
    }
}
