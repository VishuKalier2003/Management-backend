package com.managementbackend.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.managementbackend.Model.DirectorMessage;

public interface DirectorMessageRepo extends MongoRepository<DirectorMessage, String> {
    
}
