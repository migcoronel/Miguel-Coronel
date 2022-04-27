package com.example.whatsappgroupapi.repositories;

import com.example.whatsappgroupapi.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group , Long> {

//    @Query(nativeQuery = true, value = "SELECT * FROM group_table WHERE :participantId = ANY(participant_ids)")
//    List<Group> findAllforParticipant(@Param("participantId")Long participantId);
}
