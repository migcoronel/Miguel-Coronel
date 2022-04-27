package com.example.whatsappgroupapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostMessageVO {
    private Long from;
    private Long group;
    private String content;

}
