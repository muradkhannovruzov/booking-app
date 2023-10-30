package com.example.reservationms.controller;

import com.example.reservationms.dto.ReservationReqDto;
import com.example.reservationms.dto.ReservationResDto;
import com.example.reservationms.response.BaseResponse;
import com.example.reservationms.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reservation")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping("/customer/list")
    public BaseResponse<Page<ReservationResDto>> getAllForProvider
            (Pageable pageable,
             @AuthenticationPrincipal Long customerId) {
        return BaseResponse
                .success(reservationService.getAllForCustomer(pageable, customerId));
    }

    @GetMapping("/customer/{id}")
    public BaseResponse<ReservationResDto> getOneForProvider
            (@PathVariable Long id,
             @AuthenticationPrincipal Long customerId) {
        return BaseResponse
                .success(reservationService.getOneForCustomer(id, customerId));
    }

    @PostMapping("/customer")
    public BaseResponse<ReservationResDto> createForProvider
            (@RequestBody ReservationReqDto requestDto,
             @AuthenticationPrincipal Long customerId) {
        return BaseResponse
                .success(reservationService.createForCustomer(requestDto, customerId));
    }

    @PutMapping("/customer/{id}")
    public BaseResponse<ReservationResDto> updateForProvider
            (@PathVariable Long id,
             @RequestBody ReservationReqDto requestDto,
             @AuthenticationPrincipal Long customerId) {
        return BaseResponse
                .success(reservationService.updateForProvider(id, requestDto, customerId));
    }

    @DeleteMapping("/customer/{id}")
    public BaseResponse<Void> deleteForProvider
            (@PathVariable Long id,
             @AuthenticationPrincipal Long providerId) {
        reservationService.deleteForProvider(id, providerId);
        return BaseResponse.success();
    }
}
