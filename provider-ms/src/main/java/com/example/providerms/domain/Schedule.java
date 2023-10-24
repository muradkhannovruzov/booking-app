package com.example.providerms.domain;

import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Data
public class Schedule {
    private LocalTime start;
    private LocalTime end;
    private List<DayOfWeek> days;
}
