package com.dondoc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserPatchResponse {
    private Long id;
    private String name;
    private Integer age;
    private Long monthlyIncome;
    private Integer targetExpenseRatio;
    private Long monthlyBudget;
    private Long dailyBudget;

}
