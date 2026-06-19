package com.dondoc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "records")
public class Recorde {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false)
    private Long amount;

    private String description;

    private String memo;

    @Column(name = "record_date", nullable = false)
    private LocalDate recordDate;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    public Recorde(Long userId, Category category, Long amount, String description, String memo, LocalDate recordDate) {
        this.userId = userId;
        this.category = category;
        this.amount = amount;
        this.description = description;
        this.memo = memo;
        this.recordDate = recordDate;
    }

    public Long getCategoryId() {
        return category.getId();
    }

    public void update(Category category, Long amount, String description, String memo, LocalDate recordDate) {
        this.category = category;
        this.amount = amount;
        this.description = description;
        this.memo = memo;
        this.recordDate = recordDate;
    }
}
