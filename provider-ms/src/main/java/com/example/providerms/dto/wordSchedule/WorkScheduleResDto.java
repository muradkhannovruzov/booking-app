package com.example.providerms.dto.wordSchedule;

import com.example.providerms.dto.breakSchedule.BreakScheduleResDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkScheduleResDto {
    private Long id;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean isActive;
    private Set<BreakScheduleResDto> breakSchedules;
}
