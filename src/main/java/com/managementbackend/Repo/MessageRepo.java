package com.managementbackend.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.managementbackend.Model.Message;

public interface MessageRepo extends MongoRepository<Message, String> {
    
}
