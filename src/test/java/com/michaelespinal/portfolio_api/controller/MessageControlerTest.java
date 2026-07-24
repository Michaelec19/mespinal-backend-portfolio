package com.michaelespinal.portfolio_api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.michaelespinal.portfolio_api.model.Message;
import com.michaelespinal.portfolio_api.service.MessageService;
import com.michaelespinal.portfolio_api.security.JwtUtil;
import com.michaelespinal.portfolio_api.repository.UserRepository;

@WebMvcTest(MessageController.class)
@AutoConfigureMockMvc(addFilters = false)
class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MessageService messageService;

    @MockitoBean
    private JwtUtil jwtUtil;

    @MockitoBean
    private UserRepository userRepository;

    @Test
    void createMessage_ReturnsCreated() throws Exception {
        Message msg = new Message();
        msg.setSenderEmail("test@test.com");
        
        when(messageService.saveMessage(any(Message.class))).thenReturn(msg);

        mockMvc.perform(post("/api/v1/messages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(msg)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.senderEmail").value("test@test.com"));
    }

    @Test
    void getAllMessages_ReturnsOk() throws Exception {
        when(messageService.getAllMessages()).thenReturn(List.of(new Message(), new Message()));

        mockMvc.perform(get("/api/v1/messages"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void markAsRead_ReturnsOk() throws Exception {
        Message msg = new Message();
        msg.setIsRead(true);

        when(messageService.markAsRead(anyLong())).thenReturn(msg);

        mockMvc.perform(patch("/api/v1/messages/1/read"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isRead").value(true));
    }
}