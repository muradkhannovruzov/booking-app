package com.example.providerms.controller;

import com.example.providerms.dto.breakSchedule.BreakScheduleResDto;
import com.example.providerms.response.BaseResponse;
import com.example.providerms.service.impl.BreakScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/break-schedule")
@RequiredArgsConstructor
public class BreakScheduleController {
    private final BreakScheduleService breakScheduleService;

    @GetMapping("/provider/work-schedule/{scheduleId}/list")
    public BaseResponse<List<BreakScheduleResDto>> getAllForWorkSchedule
            (@PathVariable Long scheduleId,
             @AuthenticationPrincipal Long providerId) {
        return BaseResponse
                .success(breakScheduleService.getAllForWorkSchedule(scheduleId, providerId));
    }

    @GetMapping("/provider/{id}")
    public BaseResponse<BreakScheduleResDto> getOneForProvider
            (@PathVariable Long id,
             @AuthenticationPrincipal Long providerId) {
        return BaseResponse
                .success(breakScheduleService.getOneForProvider(id, providerId));
    }

    @PostMapping("/provider/work-schedule/{scheduleId}")
    public BaseResponse<BreakScheduleResDto> createForProvider
            (@PathVariable Long scheduleId,
             @RequestBody BreakScheduleResDto requestDto,
             @AuthenticationPrincipal Long providerId) {
        return BaseResponse
                .success(breakScheduleService.createForProvider(scheduleId, requestDto, providerId));
    }

    @PutMapping("/provider/{id}")
    public BaseResponse<BreakScheduleResDto> updateForProvider
            (@PathVariable Long id,
             @RequestBody BreakScheduleResDto requestDto,
             @AuthenticationPrincipal Long providerId) {
        return BaseResponse
                .success(breakScheduleService.updateForProvider(id, requestDto, providerId));
    }

    @DeleteMapping("/provider/{id}")
    public BaseResponse<Void> deleteForProvider
            (@PathVariable Long id,
             @AuthenticationPrincipal Long providerId) {
        breakScheduleService.deleteForProvider(id, providerId);
        return BaseResponse.success();
    }

}
