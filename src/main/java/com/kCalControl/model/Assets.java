package com.kCalControl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Assets {

    private UserDB creationPerson;
    private LocalDateTime creationDate;
    private UserDB modificationPerson;
    private LocalDateTime modificationDate;

}
