package com.dondoc.controller;

import com.dondoc.dto.ApiResponse;
import com.dondoc.dto.settlement.MonthlySettlementResponse;
import com.dondoc.service.RecordSettlementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/records")
@Tag(name = "Records Settlement", description = "거래 결산 조회 API")
public class RecordSettlementController {

    private final RecordSettlementService recordSettlementService;

    public RecordSettlementController(RecordSettlementService recordSettlementService) {
        this.recordSettlementService = recordSettlementService;
    }

    @GetMapping("/settlement")
    @Operation(summary = "월간 결산 통계 조회", description = "사용자의 월간 결산 결과와 카테고리별 지출 통계를 조회합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "월간 결산 통계 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "올바르지 않은 월 형식", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "인증되지 않은 사용자", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류", content = @Content)
    })
    public ApiResponse<MonthlySettlementResponse> getMonthlySettlement(
            @Parameter(name = "userId", description = "사용자 ID", required = true, example = "1", in = ParameterIn.HEADER)
            @RequestHeader(value = "userId", required = false) String userId,
            @Parameter(name = "month", description = "조회할 월", required = true, example = "2026-04", in = ParameterIn.QUERY)
            @RequestParam(value = "month", required = false) String month
    ) {
        MonthlySettlementResponse response = recordSettlementService.getMonthlySettlement(userId, month);
        return ApiResponse.ok(response, "월간 결산 통계 조회 성공");
    }
}
