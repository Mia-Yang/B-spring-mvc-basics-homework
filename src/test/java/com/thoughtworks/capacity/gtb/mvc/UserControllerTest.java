package com.thoughtworks.capacity.gtb.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.capacity.gtb.mvc.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void should_register_user() throws Exception {
        User newUser = new User("Siyu", "133666", "siyu@gmail.com");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(newUser);
        mockMvc.perform(post("/register").content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void should_input_valid_username() throws Exception {
        // 用户名长度有误
        User newUser = new User("si", "133666", "siyu@gmail.com");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(newUser);
        mockMvc.perform(post("/register").content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("用户名不合法")));

        // 用户名字符有误
        User newUser2 = new User("siyu++", "133666", "siyu@gmail.com");
        String jsonString2 = objectMapper.writeValueAsString(newUser2);
        mockMvc.perform(post("/register").content(jsonString2).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("用户名不合法")));

        // 用户名为空
        User newUser3 = new User("", "133666", "siyu@gmail.com");
        String jsonString3 = objectMapper.writeValueAsString(newUser3);
        mockMvc.perform(post("/register").content(jsonString3).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("用户名不合法")));
    }

    @Test
    void should_input_valid_password () throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        User newUser = new User("siyu", "133", "siyu@gmail.com");
        String jsonString = objectMapper.writeValueAsString(newUser);
        mockMvc.perform(post("/register").content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("密码不合法")));
    }

    @Test
    void should_input_valid_email () throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        User newUser = new User("siyu", "133666", "siyugmail.com");
        String jsonString = objectMapper.writeValueAsString(newUser);
        mockMvc.perform(post("/register").content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("邮箱地址不合法")));
    }
}
