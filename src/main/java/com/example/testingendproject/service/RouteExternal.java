package com.example.testingendproject.service;

import com.example.testingendproject.model.Route;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RouteExternal {
    public List<Route> getRoutesFromSupplier1();
    public List<Route> getRoutesFromSupplier2();
    public List<Route> getRoutesFromSupplier3();
}
