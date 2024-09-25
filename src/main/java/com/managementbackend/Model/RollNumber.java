package com.managementbackend.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "rolldata")
public class RollNumber
{
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RollItem
    {
        @Nullable
        private String roll;
        @Nullable
        private String name;
    }
    @Id
    private String id;
    @Nullable
    private List<RollItem> indexList;
}
