package com.michaelespinal.portfolio_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michaelespinal.portfolio_api.model.Message;
import com.michaelespinal.portfolio_api.repository.MessageRepository;


@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message saveMessage(Message message) {
        if (message.getSenderEmail() == null || message.getSenderEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("The SenderEmail cannot be null or empty");
        }
        if (!message.getSenderEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("The email format is invalid");
        }
        if (message.getSubject() == null || message.getSubject().trim().isEmpty()) {
            throw new IllegalArgumentException("The subject cannot be null or empty");
        }
        if (message.getContent() == null || message.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("The Content cannot be null or empty");
        }

        message.setIsRead(false);

        return messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message markAsRead(Long id) {
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("The Message with ID " + id + " does not exist."));
        
        message.setIsRead(true);
        return messageRepository.save(message);
    }
}
