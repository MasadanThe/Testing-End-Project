package com.example.testingendproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // AUTO_INCREMENT
    private long id;
    private String destinationStart;
    private String destinationEnd;
    private String transportType;
    private String estimatedDeparture;
    private String estimatedArrival;
    private long price;
    private String contractor;
    private long salePrice;
}
