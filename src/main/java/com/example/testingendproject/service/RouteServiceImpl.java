package com.example.testingendproject.service;

import com.example.testingendproject.model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("routeService")
public class RouteServiceImpl implements RouteService{

    @Autowired
    RouteExternal routeExternal;
    @Override
    public void updateSale(){

    }

    @Override
    public List<Route> getRoutes(){
        List<Route> routeList = new ArrayList<>();

        routeList.add(getRoutesFromSupplier1());
        return routeList;
    }

    @Override
    public void createBookingSupplier(){

    }
}
