package com.example.providerms.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Service {
    private Long id;
    private String name;
    private String description;
    private Integer duration;
    private BigDecimal price;
    private Schedule availabilitySchedule;
    private Long providerId;
}
