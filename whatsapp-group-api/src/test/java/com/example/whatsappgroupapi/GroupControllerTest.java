package com.example.whatsappgroupapi;

import com.example.whatsappgroupapi.controllers.GroupController;
import com.example.whatsappgroupapi.models.GroupVO;
import com.example.whatsappgroupapi.services.GroupService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.HashSet;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GroupController.class)
public class GroupControllerTest  {
        @Autowired
        private MockMvc mockMvc;
        @MockBean
        private GroupService groupService;

        @Test
        public void createNewGroup_success() throws Exception {
            ObjectMapper mapper = new ObjectMapper();

            String groupName = "Group123";
            GroupVO groupVO = new GroupVO(
                    1136693875L,
                    null,
                    new HashSet<>(Arrays.asList(1136693875L,1136693876L))
            );

            String answer = "El grupo con el nombre: " + groupName + " y el UUID: cadfcf7d-a3d3-4a34-8c07-eaa54c21821d ha sido creado con exito.";
            Mockito.when(groupService.createNewGroup(groupName,groupVO))
                    .thenReturn(answer);

            MvcResult result =  mockMvc.perform(MockMvcRequestBuilders
                    .post("/group/create/"+groupName)
                    .content(mapper.writeValueAsString(groupVO))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk()).andReturn();

            Assertions.assertEquals(result.getResponse().getContentAsString(),answer);
        }



}
