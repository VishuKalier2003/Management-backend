package com.managementbackend.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "adminlist")
public class AdminList {
    @Id
    private String id;
    @NonNull
    private String name;
    @NonNull
    private String admin;
    @NonNull
    private boolean accessKey;
}
