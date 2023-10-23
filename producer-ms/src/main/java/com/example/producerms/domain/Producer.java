package com.example.producerms.domain;

import com.example.usermanagementms.domain.User;
import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
public class Producer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
