package com.managementbackend.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.managementbackend.Model.RollNumber;

public interface RollNumberRepo extends MongoRepository<RollNumber, String> {
    
}
