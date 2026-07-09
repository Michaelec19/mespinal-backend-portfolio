package com.michaelespinal.portfolio_api.repository;

import com.michaelespinal.portfolio_api.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
