package com.dondoc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RecordSummary {
    private Long totalIncome;
    private Long totalExpense;
    private Long balance;
}
