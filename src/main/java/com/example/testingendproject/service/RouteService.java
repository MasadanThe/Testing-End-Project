package com.example.testingendproject.service;

import com.example.testingendproject.model.Route;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface RouteService {

    public boolean updateSale(Long id, Long salePrice, String username);
    public List<Route> getRoutes();

    public void setRoutes();

    public void createBookingSupplier(Route route);
}
