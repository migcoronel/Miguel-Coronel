package com.example.whatsappgroupapi.repositories;

import com.example.whatsappgroupapi.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group , Long> {

    Optional<Group> findByGroupName(String groupName);
}
