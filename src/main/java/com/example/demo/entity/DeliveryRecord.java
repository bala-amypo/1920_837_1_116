package com.example.demo.entity;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryRecord {

    private Long id;
    private Contract contract;
    private LocalDate deliveryDate;
}
