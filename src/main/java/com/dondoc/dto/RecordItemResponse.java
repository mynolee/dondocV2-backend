package com.dondoc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RecordItemResponse {
    private Long id;
    private String type;
    private String date;
    private CategoryInfo category;
    private Long amount;
    private String description;
    private String memo;
}
