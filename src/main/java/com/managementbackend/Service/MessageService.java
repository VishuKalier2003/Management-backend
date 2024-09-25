package com.managementbackend.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.managementbackend.Model.DirectorMessage;
import com.managementbackend.Model.Message;
import com.managementbackend.Model.Message.MessageBox;
import com.managementbackend.Repo.DirectorMessageRepo;
import com.managementbackend.Repo.MessageRepo;

@Service
public class MessageService {
    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private DirectorMessageRepo directorMessageRepo;

    private final String managementAPI = "https://management-app-404814.et.r.appspot.com";
    private final WebClient.Builder webClient;

    public MessageService(WebClient.Builder webClient) {
        this.webClient = webClient;
    }

    // ! Admin Message Model Services...

    public Message FindAdminMessage(String admin) {
        try {
            for (Message message : messageRepo.findAll()) {
                if (message.getAdmin().equals(admin))
                    return message;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public ResponseEntity<?> AddAdminMessage(Message message) {
        try {
            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String date = currentTime.format(format);
            Message existingMessage = FindAdminMessage(message.getAdmin());
            // If Admin has sent messages at least once...
            if (existingMessage != null) {
                List<MessageBox> messages = existingMessage.getMessageList();
                if (messages == null) {
                    messages = new ArrayList<MessageBox>();
                }
                MessageBox messageBox = new MessageBox(message.getMessage(), date);
                messages.add(messageBox);
                existingMessage.setMessageList(messages);
                messageRepo.save(existingMessage);
            } else {
                // Otherwise, the admin has not sent any message yet...
                List<MessageBox> messagesNew = new ArrayList<MessageBox>();
                MessageBox messageBox = new MessageBox(message.getMessage(), date);
                messagesNew.add(messageBox);
                message.setMessageList(messagesNew);
                messageRepo.save(message);
            }

            return ResponseEntity.ok().body("Message or Messages Added Successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error Adding Message: " + e.getMessage());
        }
    }

    public List<Message> GetAllMessages() {
        return messageRepo.findAll();
    }

    @Scheduled(cron = "0 0 */12 * * *")
    public ResponseEntity<?> DeleteAfterEachDay() {
        try {
            for (Message message : messageRepo.findAll()) {
                if (message.getMessageList() != null) {
                    List<MessageBox> updatedMessageBoxes = new ArrayList<>();
                    for (MessageBox messageBox : message.getMessageList()) {
                        LocalDateTime currentTime = LocalDateTime.now();
                        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        String date = currentTime.format(format);
                        LocalDateTime dateTime = LocalDateTime.parse(messageBox.getDateTime(), format);
                        if (dateTime.plusHours(12).isAfter(LocalDateTime.parse(date, format))) {
                            updatedMessageBoxes.add(messageBox);
                        }
                    }
                    message.setMessageList(updatedMessageBoxes);
                    messageRepo.save(message);
                }
            }
            return ResponseEntity.ok().body("Messages Updated Successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error Updating Messages");
        }
    }

    // ! Director Message Model Services...

    public DirectorMessage FindDirectorMessage(String adminName, String directorName) {
        try {
            for (DirectorMessage message : directorMessageRepo.findAll()) {
                if (message.getAdmin().equals(adminName) && message.getDirectorName().equals(directorName))
                    return message;
            }
            return null;
        } catch (Exception e) {
            ResponseEntity.badRequest().body("Error Finding Director Message: " + e.getMessage());
            return null;
        }
    }

    // Adding the Director Message to the Repository...
    public ResponseEntity<?> AddDirectorMessage(DirectorMessage message) {
        try {
            Boolean response = webClient.build().post().uri(managementAPI+"/admin/check/"+message.getAdmin()).
            retrieve().bodyToMono(Boolean.class).block();
            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String date = currentTime.format(format);
            message.setDateTime(date);
            directorMessageRepo.save(message);
            return ResponseEntity.ok().body("Director Message Added Successfully"+response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error Adding Director Message: " + e.getMessage());
        }
    }

    // Getting the List of All Director Messages...
    public List<DirectorMessage> GetAllDirectorMessages() {
        return directorMessageRepo.findAll();
    }

    // Deleting the Director Message
    public ResponseEntity<?> DeleteDirectorMessage(String adminName, String directorName) {
        try {
            DirectorMessage existingMessage = FindDirectorMessage(adminName, directorName);
            if (existingMessage != null) {
                directorMessageRepo.delete(existingMessage);
            } else
                return ResponseEntity.badRequest().body("Error Deleting Director Message: Director Message Not Found");
            return ResponseEntity.ok().body("Director Message Deleted Successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error Deleting Director Message: " + e.getMessage());
        }
    }
}