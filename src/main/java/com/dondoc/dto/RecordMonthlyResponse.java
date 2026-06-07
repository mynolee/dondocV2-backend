package com.dondoc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class RecordMonthlyResponse {
    private RecordSummary summary;
    private List<RecordItemResponse> records;
}
