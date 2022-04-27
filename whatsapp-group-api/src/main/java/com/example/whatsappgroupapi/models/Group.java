package com.example.whatsappgroupapi.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "group_table")
@NoArgsConstructor
@AllArgsConstructor
public class Group extends BaseEntity{

    private LocalDateTime dateStarted;

    private String name;

    @ManyToMany
    private List<User> participants;

    public Group(List<User> participants, String name){
        this.dateStarted = LocalDateTime.now();
        this.participants = participants;
        this.name = name;

    }

}
