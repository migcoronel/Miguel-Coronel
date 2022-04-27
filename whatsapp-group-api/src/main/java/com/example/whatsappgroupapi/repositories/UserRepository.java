package com.example.whatsappgroupapi.repositories;

import com.example.whatsappgroupapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User , Long> {

}
