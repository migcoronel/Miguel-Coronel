package com.example.whatsappgroupapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table (name = "message")
@NoArgsConstructor
@AllArgsConstructor
public class Message extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User from;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    private String content;

    private LocalDateTime dateSent;
    private LocalDateTime dateDelivered;
    private LocalDateTime dateViewed;

    public Message(User from, Group group, String content){
        this.from = from;
        this.group = group;
        this.content = content;
        this.dateSent = LocalDateTime.now();
    }

}
