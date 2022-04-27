package com.example.whatsappgroupapi;

import com.example.whatsappgroupapi.controllers.MessageController;
import com.example.whatsappgroupapi.models.PostMessageVO;
import com.example.whatsappgroupapi.services.MessageService;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MessageController.class)
public class MessageControllerTest  {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MessageService messageService;

    @Test
    public void postMessage_success() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        PostMessageVO postMessageVO =
                new PostMessageVO(1136693875L,"groupA","This is a test message");

        String answer = "Mesaje posteado correctamente en el grupo groupA.";
        Mockito.when(messageService.postMessage(postMessageVO))
                .thenReturn(answer);

        MvcResult result =  mockMvc.perform(MockMvcRequestBuilders
                .post("/message/post")
                .content(mapper.writeValueAsString(postMessageVO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        Assertions.assertEquals(result.getResponse().getContentAsString(),answer);
    }



}
