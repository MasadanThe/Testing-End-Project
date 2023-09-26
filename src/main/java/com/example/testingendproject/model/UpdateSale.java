package com.example.testingendproject.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UpdateSale {
    Long id;
    Long salePrice;
    String username;
}
