package com.example.servicecatalogms.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
public class ServiceCatalogResDto {
    private Long id;
    private String name;
    private String description;
    private Integer duration;
    private BigDecimal price;
    private Long providerId;
}
