package com.managementbackend.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.managementbackend.Model.AdminList;

public interface AdminListRepo extends MongoRepository<AdminList, String> {
    
}
