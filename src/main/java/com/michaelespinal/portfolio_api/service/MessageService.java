package com.michaelespinal.portfolio_api.service;

import com.michaelespinal.portfolio_api.model.Message;
import com.michaelespinal.portfolio_api.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


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
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }
}
