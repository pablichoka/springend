package com.kCalControl.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RetrieveUsersDTO {

    private long numOfResults;
    private List<RetrieveUserDTO> users;

}
