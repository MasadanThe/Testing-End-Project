package com.example.testingendproject.repository;

import com.example.testingendproject.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    Route findAllByContractor(String contractor);
    Optional<Route> findById(Long id);
}
