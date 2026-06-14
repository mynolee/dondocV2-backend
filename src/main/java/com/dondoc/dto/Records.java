package com.dondoc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Records {
    private Long id;
    private Long userId;
    private Long categoryId;
    private Long amount;
    private String description;
    private String memo;
    private LocalDate recordDate;
    private LocalDateTime createdAt;

    @Getter
    @AllArgsConstructor
    public static class MonthlySettlementResponse {
        private final String month;
        private final Long totalIncome;
        private final Long totalExpense;
        private final Long netIncome;
        private final Long monthlyBudget;
        private final Integer budgetUsedPercent;
        private final Integer avgPigState;
        private final Integer currentHouseLevel;
        private final Integer nextHouseLevel;
        private final List<SettlementCategoryExpense> categoryExpenses;
    }

    @Getter
    @AllArgsConstructor
    public static class SettlementCategoryExpense {
        private final SettlementCategory category;
        private final Long amount;
        private final Integer ratio;
    }

    @Getter
    @AllArgsConstructor
    public static class SettlementCategory {
        private final Long id;
        private final String name;
    }
}
