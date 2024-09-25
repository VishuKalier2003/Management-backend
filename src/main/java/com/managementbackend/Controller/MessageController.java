package com.managementbackend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.managementbackend.Model.DirectorMessage;
import com.managementbackend.Model.Message;
import com.managementbackend.Service.MessageService;

@RestController
@RequestMapping("/backend/message")
@CrossOrigin(origins = "*")
public class MessageController {

    @Autowired
    private MessageService messageService;

    //! POST Mapping...

    @PostMapping("/add")
    public ResponseEntity<?> AddAdminMessage(@RequestBody Message message) {
        return messageService.AddAdminMessage(message);
    }

    @PostMapping("/addDirector")
    public ResponseEntity<?> AddDirectorMessage(@RequestBody DirectorMessage message) {
        return messageService.AddDirectorMessage(message);
    }

    //! GET Mapping...

    @GetMapping("/getAll")
    public List<Message> GetAllMessages() {
        return messageService.GetAllMessages();
    }

    @GetMapping("/getDirectorAll")
    public List<DirectorMessage> GetAllDirectorMessages() {
        return messageService.GetAllDirectorMessages();
    }

    //! DELETE Mapping...

    @DeleteMapping("/delete/{admin}/{director}")
    public ResponseEntity<?> DeleteMessage(@PathVariable("admin") String admin, @PathVariable("director") String director) {
        return messageService.DeleteDirectorMessage(admin, director);
    }
}