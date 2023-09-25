package com.example.testingendproject.service;

import com.example.testingendproject.model.Route;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public interface RouteService {

    public void updateSale();

    public List<Route> getRoutes();
    public List<Route> getRoutesFromSupplier1();
    public List<Route> getRoutesFromSupplier2();
    public List<Route> getRoutesFromSupplier3();
    public List<Route> getRoutesFromSupplier4();

    public void createBookingSupplier();
}
