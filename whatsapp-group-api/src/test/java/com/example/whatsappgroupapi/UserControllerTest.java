package com.example.whatsappgroupapi;

import com.example.whatsappgroupapi.controllers.UserController;
import com.example.whatsappgroupapi.models.User;
import com.example.whatsappgroupapi.services.UserService;
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
import java.util.LinkedHashSet;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    User RECORD_1 = new User(1136693875L);
    User RECORD_2 = new User(1136693876L);
    User RECORD_3 = new User(1136693877L);

    @Test
    public void getAllUsers_success() throws Exception {
        LinkedHashSet<User> records = new LinkedHashSet<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));
        Mockito.when(userService.getAllUsers()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/user")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].dateCreated", is(notNullValue())))
                .andExpect(jsonPath("$[1].dateCreated", is(notNullValue())))
                .andExpect(jsonPath("$[2].dateCreated", is(notNullValue())));
    }

    @Test
    public void registerNewUser_success() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("/user/1136693889")
                .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andReturn();

    }



}
