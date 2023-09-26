package com.example.testingendproject.model;

import jakarta.persistence.Entity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FastDeleteAccount {
    String id;
    String username;
}
