package com.dondoc.repository;

import com.dondoc.entity.Farm;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class FarmRepository {

    private final JdbcTemplate jdbcTemplate;

    public FarmRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Farm> findAll(){
        String sql = "SELECT * FROM farms";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Farm(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getObject("created_at", LocalDateTime.class)
        ));
    }

    //  - deleteByFarmIdAndUserId → farm_members에서 해당 유저 row 삭제
    //  - countByFarmId → 탈퇴 후 남은 멤버 수 확인 (0이면 농장도 삭제해야 함)
    public void save(Farm farm) {
        String sql = "INSERT INTO farms (name, created_at) VALUES (?, ?)";
        jdbcTemplate.update(sql, farm.getName(), farm.getCreatedAt());
    }

    // 멤버가 0명이 되면 농장 자체도 삭제해야 하는데, 그때 이 메서드를 호출할 거야.
    public void deleteById(Long farmId) {
        String sql = "DELETE FROM farms WHERE id = ?";
        jdbcTemplate.update(sql, farmId);
    }



}
