package com.portfolio.repository;

import com.portfolio.model.ContactMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ContactMessageRepository extends MongoRepository<ContactMessage, String> {
    List<ContactMessage> findByIsReadFalseOrderByCreatedAtDesc();
    List<ContactMessage> findAllByOrderByCreatedAtDesc();
}
