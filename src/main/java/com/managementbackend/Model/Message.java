package com.managementbackend.Model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;
import com.mongodb.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "adminmessage")
public class Message {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class MessageBox {
        @NonNull
        private String data;
        @Nullable
        private String dateTime;
        public MessageBox(String data, String dateTime) {
            this.data = data;
            this.dateTime = dateTime;
        }
    }

    @Id
    private String id;
    @NonNull
    private String admin;
    @NonNull
    private String message;
    @Nullable
    private List<MessageBox> messageList;
}
