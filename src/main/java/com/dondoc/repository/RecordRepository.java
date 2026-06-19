package com.dondoc.repository;

import com.dondoc.entity.Recorde;
import com.dondoc.repository.projection.CategoryAmountSummary;
import com.dondoc.repository.projection.ExpenseCategorySummary;
import com.dondoc.repository.projection.MonthlyRecordAmountSummary;
import com.dondoc.repository.projection.MonthlyRecordTotal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecordRepository extends JpaRepository<Recorde, Long> {

    @Query("""
            select r
            from Recorde r
            join fetch r.category
            where r.userId = :userId
              and r.recordDate between :start and :end
            """)
    List<Recorde> findByDateRange(
            @Param("userId") long userId,
            @Param("start") LocalDate start,
            @Param("end") LocalDate end);

    @Query("""
            select r
            from Recorde r
            join fetch r.category c
            where r.userId = :userId
              and r.recordDate >= :startDate
              and r.recordDate < :endDate
              and (:type is null or c.type = :type)
            """)
    List<Recorde> findByUserMonth(
            @Param("userId") Long userId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("type") String type);

    @Query("""
            select new com.dondoc.repository.projection.MonthlyRecordTotal(
                coalesce(sum(case
                    when upper(c.type) = 'INCOME' or c.type = '수입' then r.amount
                    else 0
                end), 0),
                coalesce(sum(case
                    when upper(c.type) = 'EXPENSE' or c.type = '지출' then r.amount
                    else 0
                end), 0),
                count(r.id)
            )
            from Recorde r
            left join r.category c
            where r.userId = :userId
              and r.recordDate >= :startDate
              and r.recordDate < :endDate
            """)
    MonthlyRecordTotal findMonthlyTotal(
            @Param("userId") Long userId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    @Query("""
            select new com.dondoc.repository.projection.CategoryAmountSummary(
                c.id,
                c.name,
                c.type,
                sum(r.amount)
            )
            from Recorde r
            join r.category c
            where r.userId = :userId
              and r.recordDate >= :startDate
              and r.recordDate < :endDate
            group by c.id, c.name, c.type
            order by sum(r.amount) desc, c.id asc
            """)
    List<CategoryAmountSummary> findMonthlyCategoryAmounts(
            @Param("userId") Long userId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    @Query("""
            select new com.dondoc.repository.projection.MonthlyRecordAmountSummary(
                coalesce(sum(case
                    when upper(c.type) = 'INCOME' or c.type = '수입' then r.amount
                    else 0
                end), 0),
                coalesce(sum(case
                    when upper(c.type) = 'EXPENSE' or c.type = '지출' then r.amount
                    else 0
                end), 0)
            )
            from Recorde r
            join r.category c
            where r.userId = :userId
              and r.recordDate >= :startDate
              and r.recordDate < :endDate
            """)
    MonthlyRecordAmountSummary findMonthlyAmountSummary(
            @Param("userId") Long userId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    @Query("""
            select new com.dondoc.repository.projection.ExpenseCategorySummary(
                c.id,
                c.name,
                sum(r.amount)
            )
            from Recorde r
            join r.category c
            where r.userId = :userId
              and r.recordDate >= :startDate
              and r.recordDate < :endDate
              and (upper(c.type) = 'EXPENSE' or c.type = '지출')
            group by c.id, c.name
            order by sum(r.amount) desc, c.id asc
            """)
    List<ExpenseCategorySummary> findMonthlyExpenseCategories(
            @Param("userId") Long userId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    @Query("""
            select new com.dondoc.repository.projection.MonthlyRecordAmountSummary(
                coalesce(sum(case
                    when c.type = 'INCOME' then r.amount
                    else 0
                end), 0),
                coalesce(sum(case
                    when c.type = 'EXPENSE' then r.amount
                    else 0
                end), 0)
            )
            from Recorde r
            join r.category c
            where r.userId = :userId
              and r.recordDate >= :startDate
              and r.recordDate < :endDate
              and (:type is null or c.type = :type)
            """)
    MonthlyRecordAmountSummary findSummaryByUserMonth(
            @Param("userId") Long userId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("type") String type);
}
