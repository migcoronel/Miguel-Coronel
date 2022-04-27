package com.example.whatsappgroupapi.repositories;

import com.example.whatsappgroupapi.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {
}
