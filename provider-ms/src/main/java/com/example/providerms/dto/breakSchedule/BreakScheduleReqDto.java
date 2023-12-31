package com.example.providerms.dto.breakSchedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BreakScheduleReqDto {

    private LocalTime startTime;

    private LocalTime endTime;
}
