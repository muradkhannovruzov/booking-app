package com.example.providerms.dto.wordSchedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkScheduleReqDto {
    private DayOfWeek dayOfWeek;

    private LocalTime startTime;

    private LocalTime endTime;

    private boolean isActive;
}
