package com.managementbackend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.managementbackend.Model.RollNumber;
import com.managementbackend.Service.RollNumberService;

@RestController
@RequestMapping("/backend/roll")
@CrossOrigin(origins = "http://localhost:3000")
public class RollController {
    
    @Autowired
    private RollNumberService rollNumberService;

    @PostMapping("/add/{rollName}/{name}")
    public void AddRollNumber(@PathVariable("rollName") String rollName, @PathVariable("name") String name)
    {
        rollNumberService.SaveRollNumber(rollName, name);
    }

    @GetMapping("/getAll")
    public List<RollNumber.RollItem> GetAllRollNumber()
    {
        return rollNumberService.GetRollNumberList();
    }
}
