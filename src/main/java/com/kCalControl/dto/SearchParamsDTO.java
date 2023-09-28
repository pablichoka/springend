package com.kCalControl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchParamsDTO {

    int page;
    int pageSize;
    String query;
    String sort;

}
