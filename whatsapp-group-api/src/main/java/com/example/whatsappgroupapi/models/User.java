package com.example.whatsappgroupapi.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table (name = "user_table")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private Long phoneNumber;

    private LocalDateTime dateCreated;

    public User(Long phoneNumber){
        this.phoneNumber = phoneNumber;
        this.dateCreated = LocalDateTime.now();
    }

}
