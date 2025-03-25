package com.atharva.journalapp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "check_list")
@Data
@NoArgsConstructor
public class CheckList {
    @Id
    private ObjectId id;
    @NonNull
    private String title;
    private boolean checked;

}

