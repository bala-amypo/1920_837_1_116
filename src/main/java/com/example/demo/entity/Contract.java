package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contractName;

    private LocalDate startDate;
    private LocalDate endDate;

    private Double contractValue;

    private Boolean active = true;
}
