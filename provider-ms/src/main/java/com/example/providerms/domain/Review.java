package com.example.providerms.domain;

import lombok.Data;

@Data
public class Review {
    private Long id;
    private Long rating;
    private String comment;

    private Long customerId;
    private Long serviceId;
}
