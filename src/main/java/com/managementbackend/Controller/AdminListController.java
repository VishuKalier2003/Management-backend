package com.managementbackend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.managementbackend.Model.AdminList;
import com.managementbackend.Service.AdminListService;

import java.util.*;

@RestController
@RequestMapping("/backend/admin")
@CrossOrigin(origins = "*")
public class AdminListController {
    @Autowired
    private AdminListService adminListService;

    @PostMapping("/add")
    public ResponseEntity<?> AddAdminCredentials(@RequestBody AdminList admin) {
        return adminListService.AddAdminCredentials(admin);
    }

    @GetMapping("/get/{adminName}")
    public AdminList GetAdminList(@PathVariable("adminName") String adminName) {
        return adminListService.GetAdminCredentials(adminName);
    }

    @GetMapping("/getAll")
    public List<AdminList> GetAll() {
        return adminListService.GetAllAdminCredentials();
    }
}
