package com.example.servicecatalogms.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ServiceCatalogReqDto {
    private String name;
    private String description;
    private Integer duration;
    private BigDecimal price;
}
