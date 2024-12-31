package com.aman.Journal.App.entity;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "journals")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Journal {

    @Id
    private ObjectId id;
    @NonNull
    private String title;
    private String description;
    private LocalDateTime date;
}
