package com.example.reservationms.service;

import com.example.reservationms.domain.Reservation;
import com.example.reservationms.dto.ReservationReqDto;
import com.example.reservationms.dto.ReservationResDto;
import com.example.reservationms.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ModelMapper modelMapper;

    public Page<ReservationResDto> getAllForCustomer(Pageable pageable, Long customerId) {
        return reservationRepository.findAllByCustomerId(pageable, customerId)
                .map(reservation -> modelMapper.map(reservation, ReservationResDto.class));
    }

    public ReservationResDto getOneForCustomer(Long id, Long customerId) {
        return modelMapper.map(reservationRepository.findById(id).orElseThrow(), ReservationResDto.class);
    }

    public ReservationResDto createForCustomer(ReservationReqDto requestDto, Long customerId) {
        return modelMapper.map(reservationRepository
                .save(modelMapper.map(requestDto, Reservation.class)), ReservationResDto.class);
    }

    public ReservationResDto updateForProvider(Long id, ReservationReqDto requestDto, Long customerId) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow();
        reservation.setCustomerId(customerId);
        reservation.setServiceId(requestDto.getServiceId());
        reservation.setStatus(requestDto.getStatus());
        reservation.setStartTime(requestDto.getStartTime());
        reservation.setEndTime(requestDto.getEndTime());
        reservation.setUpdatedAt(LocalDateTime.now());
        return modelMapper.map(reservationRepository.save(reservation), ReservationResDto.class);
    }

    public void deleteForProvider(Long id, Long providerId) {
        reservationRepository.deleteById(id);
    }
}
