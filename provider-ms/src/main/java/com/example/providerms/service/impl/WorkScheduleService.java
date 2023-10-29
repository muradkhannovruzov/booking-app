package com.example.providerms.service.impl;

import com.example.providerms.domain.Provider;
import com.example.providerms.domain.WorkSchedule;
import com.example.providerms.dto.wordSchedule.WorkScheduleReqDto;
import com.example.providerms.dto.wordSchedule.WorkScheduleResDto;
import com.example.providerms.exception.BaseException;
import com.example.providerms.repository.ProviderRepository;
import com.example.providerms.repository.WorkScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import static com.example.providerms.response.enums.ErrorResponseMessages.FORBIDDEN;

@Service
@RequiredArgsConstructor
public class WorkScheduleService {

    private final WorkScheduleRepository workScheduleRepository;
    private final ProviderRepository providerRepository;
    private final ModelMapper modelMapper;

    public Page<WorkScheduleResDto> getAllForProvider(Pageable pageable, Long providerId) {
        return workScheduleRepository.findAllByProviderId(pageable, providerId)
                .map(workSchedule -> modelMapper.map(workSchedule, WorkScheduleResDto.class));
    }


    public WorkScheduleResDto getOneForProvider(Long id, Long providerId) {
        WorkSchedule workSchedule = workScheduleRepository.findById(id)
                .orElseThrow(
                        () -> BaseException.notFound(
                                WorkSchedule.class.getSimpleName(),
                                "id",
                                id.toString()));
        if(!workSchedule.getProvider().getId().equals(providerId)){
            throw BaseException.of(FORBIDDEN);
        }else {
            return modelMapper.map(workSchedule, WorkScheduleResDto.class);
        }
    }

    public WorkScheduleResDto createForProvider(WorkScheduleReqDto requestDto, Long providerId) {
        WorkSchedule workSchedule = modelMapper.map(requestDto, WorkSchedule.class);
        workSchedule.setProvider(providerRepository.findById(providerId)
                .orElseThrow(
                        () -> BaseException.notFound(
                                Provider.class.getSimpleName(),
                                "providerId",
                                providerId.toString())));
        return modelMapper.map(workScheduleRepository.save(workSchedule), WorkScheduleResDto.class);
    }

    public WorkScheduleResDto updateForProvider
            (Long id,
             WorkScheduleReqDto requestDto,
             Long providerId) {

        WorkSchedule workSchedule = workScheduleRepository.findById(id)
                .orElseThrow(
                        () -> BaseException.notFound(
                                WorkSchedule.class.getSimpleName(),
                                "id",
                                id.toString()));

        if (!workSchedule.getProvider().getId().equals(providerId)){
            throw BaseException.of(FORBIDDEN);
        } else {
            workSchedule.setDayOfWeek(requestDto.getDayOfWeek());
            workSchedule.setStartTime(requestDto.getStartTime());
            workSchedule.setEndTime(requestDto.getEndTime());
            workSchedule.setActive(requestDto.isActive());
            return modelMapper.map(workScheduleRepository.save(workSchedule), WorkScheduleResDto.class);
        }
    }

    public void deleteForProvider(Long id, Long providerId) {
        WorkSchedule workSchedule = workScheduleRepository.findById(id)
                .orElseThrow(
                        () -> BaseException.notFound(
                                WorkSchedule.class.getSimpleName(),
                                "id",
                                id.toString()));
        if (!workSchedule.getProvider().getId().equals(providerId)){
            throw BaseException.of(FORBIDDEN);
        } else {
            workScheduleRepository.delete(workSchedule);
        }
    }

    public boolean existsByIdAndProviderId(Long workScheduleId, Long providerId) {
        return workScheduleRepository.findByIdAndProviderId(workScheduleId, providerId)
                .isPresent();
    }

    public WorkSchedule getById(Long scheduleId) {
        return workScheduleRepository.findById(scheduleId)
                .orElseThrow(
                        () -> BaseException.notFound(
                                WorkSchedule.class.getSimpleName(),
                                "id",
                                scheduleId.toString()));
    }
}
