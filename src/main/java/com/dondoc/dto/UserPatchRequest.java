package com.dondoc.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserPatchRequest {
    private String name;
    private Integer age;
    private Long monthlyIncome;
    private Integer targetExpenseRatio;
}
