package com.managementbackend.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;
import com.mongodb.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "adminmessageDirect")
public class DirectorMessage
{
    @Id
    String id;
    @NonNull
    String admin;
    @NonNull
    String directorName;
    @NonNull
    String message;
    @Nullable
    String dateTime;
}
