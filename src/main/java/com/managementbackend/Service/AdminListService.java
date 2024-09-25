package com.managementbackend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.managementbackend.Model.AdminList;
import com.managementbackend.Repo.AdminListRepo;

import java.util.*;

@Service
public class AdminListService {

    @Autowired
    private AdminListRepo adminListRepo;

    public ResponseEntity<?> AddAdminCredentials(AdminList admin)
    {
        try{
            adminListRepo.save(admin);
            return ResponseEntity.ok("Admin Credentials Added Successfully !!. The data added is : "+admin);
        }
        catch(Exception e)
        {
            return ResponseEntity.badRequest().body("Error Occured : "+e.getMessage());
        }
    }

    public List<AdminList> GetAllAdminCredentials()
    {
        return adminListRepo.findAll();
    }

    // It is advised to use this function, when you are sure that Admin is present...
    public AdminList GetAdminCredentials(String adminName)
    {
        for(AdminList admin: adminListRepo.findAll())
        {
            if(admin.getAdmin() != null && admin.getAdmin().equals(adminName))
                return admin;
        }
        return null;            // Empty Object returned...
    }
}
