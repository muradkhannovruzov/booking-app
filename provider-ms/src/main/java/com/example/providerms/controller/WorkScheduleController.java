package com.example.providerms.controller;

import com.example.providerms.dto.wordSchedule.WorkScheduleReqDto;
import com.example.providerms.dto.wordSchedule.WorkScheduleResDto;
import com.example.providerms.response.BaseResponse;
import com.example.providerms.service.impl.WorkScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/work-schedule")
@RequiredArgsConstructor
public class WorkScheduleController {
    private final WorkScheduleService workScheduleService;

    @GetMapping("/provider/list")
    public BaseResponse<Page<WorkScheduleResDto>> getAllForProvider
            (Pageable pageable,
             @AuthenticationPrincipal Long providerId) {
        return BaseResponse
                .success(workScheduleService.getAllForProvider(pageable, providerId));
    }

    @GetMapping("/provider/{id}")
    public BaseResponse<WorkScheduleResDto> getOneForProvider
            (@PathVariable Long id,
             @AuthenticationPrincipal Long providerId) {
        return BaseResponse
                .success(workScheduleService.getOneForProvider(id, providerId));
    }

    @PostMapping("/provider")
    public BaseResponse<WorkScheduleResDto> createForProvider
            (@RequestBody WorkScheduleReqDto requestDto,
             @AuthenticationPrincipal Long providerId) {
        return BaseResponse
                .success(workScheduleService.createForProvider(requestDto, providerId));
    }

    @PutMapping("/provider/{id}")
    public BaseResponse<WorkScheduleResDto> updateForProvider
            (@PathVariable Long id,
             @RequestBody WorkScheduleReqDto requestDto,
             @AuthenticationPrincipal Long providerId) {
        return BaseResponse
                .success(workScheduleService.updateForProvider(id, requestDto, providerId));
    }

    @DeleteMapping("/provider/{id}")
    public BaseResponse<Void> deleteForProvider
            (@PathVariable Long id,
             @AuthenticationPrincipal Long providerId) {
        workScheduleService.deleteForProvider(id, providerId);
        return BaseResponse.success();
    }
}
