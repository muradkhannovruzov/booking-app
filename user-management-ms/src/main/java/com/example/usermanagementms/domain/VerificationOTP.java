package com.example.usermanagementms.domain;

import com.example.usermanagementms.domain.enums.VerificationType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenerationTime;

import java.time.LocalDateTime;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = VerificationOTP.TABLE_NAME)
public class VerificationOTP {

    public static final String TABLE_NAME = "verification_otp";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String otp;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime expiryDate;

    @Enumerated(EnumType.STRING)
    private VerificationType verficationType;

    @OneToOne
    @JsonIgnore
    @ToString.Exclude
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Exclude
    private User user;
}
