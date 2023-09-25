package com.example.testingendproject.service;

import com.example.testingendproject.model.Route;
import com.example.testingendproject.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("routeService")
public class RouteServiceImpl implements RouteService{

    @Autowired
    RouteExternal routeExternal;

    @Autowired
    RouteRepository routeRepository;

    @Override
    public void updateSale(){

    }

    @Override
    public List<Route> setRoutes(){
        List<Route> routeList = new ArrayList<>();

        routeList.addAll(routeExternal.getRoutesFromSupplier1());
        routeList.addAll(routeExternal.getRoutesFromSupplier2());
        routeList.addAll(routeExternal.getRoutesFromSupplier3());
        return routeList;
    }

    @Override
    public void createBookingSupplier(Route route){


    }
}
