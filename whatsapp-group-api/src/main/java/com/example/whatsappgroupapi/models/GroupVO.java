package com.example.whatsappgroupapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupVO {
    private Long from;
    private Set<Long> admins;
    private Set<Long> participants;
}
