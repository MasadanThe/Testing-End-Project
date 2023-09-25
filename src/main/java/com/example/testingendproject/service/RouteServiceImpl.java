package com.example.testingendproject.service;

import com.example.testingendproject.model.Route;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("routeService")
public class RouteServiceImpl implements RouteService{

    @Override
    public void updateSale(){

    }

    @Override
    public List<Route> getRoutes(){
        List<Route> routeList = new ArrayList<>();

        routeList.addAll(getRoutesFromSupplier1());
        return routeList;
    }
    @Override
    public List<Route> getRoutesFromSupplier1(){
        return null;
    }
    @Override
    public List<Route> getRoutesFromSupplier2(){
        return null;
    }
    @Override
    public List<Route> getRoutesFromSupplier3(){
        return null;
    }
    @Override
    public List<Route> getRoutesFromSupplier4(){
        return null;
    }

    @Override
    public void createBookingSupplier(){

    }
}
