package com.example.whatsappgroupapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "group_table")
@NoArgsConstructor
@AllArgsConstructor
public class Group extends BaseEntity{

    private LocalDateTime dateStarted;

    private String groupName;

    @ManyToMany
    private Set<User> admins;

    @ManyToMany
    private Set<User> participants;

    public Group(Set<User> admins, Set<User> participants, String groupName){
        this.dateStarted = LocalDateTime.now();
        this.admins = admins;
        this.participants = participants;
        this.groupName = groupName;

    }

}
