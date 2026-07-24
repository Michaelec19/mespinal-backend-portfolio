package com.michaelespinal.portfolio_api.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.michaelespinal.portfolio_api.model.Message;
import com.michaelespinal.portfolio_api.repository.MessageRepository;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageService messageService;

    @Test
    void saveMessage_Success() {
        Message msg = new Message();
        msg.setSenderEmail("test@test.com");
        msg.setSubject("Hello");
        msg.setContent("Message content");

        when(messageRepository.save(any(Message.class))).thenReturn(msg);

        Message saved = messageService.saveMessage(msg);

        assertNotNull(saved);
        assertFalse(saved.getIsRead());
        verify(messageRepository, times(1)).save(msg);
    }

    @Test
    void saveMessage_InvalidEmail_ThrowsException() {
        Message msg = new Message();
        msg.setSenderEmail("invalid-email");
        
        assertThrows(IllegalArgumentException.class, () -> messageService.saveMessage(msg));
        verify(messageRepository, never()).save(any());
    }

    @Test
    void getAllMessages_ReturnsList() {
        when(messageRepository.findAll()).thenReturn(List.of(new Message(), new Message()));
        assertEquals(2, messageService.getAllMessages().size());
    }

    @Test
    void markAsRead_Success() {
        Message msg = new Message();
        msg.setIsRead(false);
        when(messageRepository.findById(1L)).thenReturn(Optional.of(msg));
        when(messageRepository.save(any(Message.class))).thenReturn(msg);

        Message updatedMsg = messageService.markAsRead(1L);

        assertTrue(updatedMsg.getIsRead());
    }
}