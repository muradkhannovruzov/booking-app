package com.example.providerms.service.impl;

import com.example.providerms.domain.BreakSchedule;
import com.example.providerms.dto.breakSchedule.BreakScheduleResDto;
import com.example.providerms.exception.BaseException;
import com.example.providerms.repository.BreakScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.providerms.response.enums.ErrorResponseMessages.FORBIDDEN;

@Service
@RequiredArgsConstructor
public class BreakScheduleService {
    private final BreakScheduleRepository breakScheduleRepository;
    private final WorkScheduleService workScheduleService;
    private final ModelMapper modelMapper;
    public List<BreakScheduleResDto> getAllForWorkSchedule(Long scheduleId, Long providerId) {
        if (!workScheduleService.existsByIdAndProviderId(scheduleId, providerId)) {
            throw BaseException.of(FORBIDDEN);
        }
        return breakScheduleRepository.findAllByWorkScheduleId(scheduleId)
                .stream()
                .map(breakSchedule -> modelMapper.map(breakSchedule, BreakScheduleResDto.class))
                .toList();
    }


    public BreakScheduleResDto getOneForProvider(Long id, Long providerId) {
        BreakSchedule breakSchedule = breakScheduleRepository.findById(id).orElseThrow(
                ()->BaseException.notFound(
                        BreakScheduleResDto.class.getSimpleName(),
                        "id",
                        id.toString()
                )
        );
        if (!workScheduleService.existsByIdAndProviderId(breakSchedule.getWorkSchedule().getId(), providerId)) {
            throw BaseException.of(FORBIDDEN);
        }
        return modelMapper.map(breakSchedule, BreakScheduleResDto.class);
    }

    public BreakScheduleResDto createForProvider(Long scheduleId, BreakScheduleResDto requestDto, Long providerId) {
        BreakSchedule breakSchedule = modelMapper.map(requestDto, BreakSchedule.class);
        breakSchedule.setWorkSchedule(workScheduleService.getById(scheduleId));
        if (!workScheduleService.existsByIdAndProviderId(scheduleId, providerId)) {
            throw BaseException.of(FORBIDDEN);
        }
        return modelMapper.map(breakScheduleRepository.save(breakSchedule), BreakScheduleResDto.class);
    }

    public BreakScheduleResDto updateForProvider(Long id, BreakScheduleResDto requestDto, Long providerId) {
        BreakSchedule breakSchedule = breakScheduleRepository.findById(id).orElseThrow(
                ()->BaseException.notFound(
                        BreakScheduleResDto.class.getSimpleName(),
                        "id",
                        id.toString()
                )
        );
        if (!workScheduleService.existsByIdAndProviderId(breakSchedule.getWorkSchedule().getId(), providerId)) {
            throw BaseException.of(FORBIDDEN);
        }
        breakSchedule.setStartTime(requestDto.getStartTime());
        breakSchedule.setEndTime(requestDto.getEndTime());
        return modelMapper.map(breakScheduleRepository.save(breakSchedule), BreakScheduleResDto.class);
    }

    public void deleteForProvider(Long id, Long providerId) {
        BreakSchedule breakSchedule = breakScheduleRepository.findById(id).orElseThrow(
                ()->BaseException.notFound(
                        BreakScheduleResDto.class.getSimpleName(),
                        "id",
                        id.toString()
                )
        );
        if (!workScheduleService.existsByIdAndProviderId(breakSchedule.getWorkSchedule().getId(), providerId)) {
            throw BaseException.of(FORBIDDEN);
        }
        breakScheduleRepository.delete(breakSchedule);
    }
}
