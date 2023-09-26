package com.example.testingendproject.service;

import com.example.testingendproject.model.Route;
import com.example.testingendproject.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService{

    @Autowired(required = false)
    RouteExternal routeExternal;

    @Autowired
    RouteRepository routeRepository;



    @Override
    public boolean updateSale(Long id, Long salePrice, String username){
        List<Route> routeList = getRoutes();
        Route foundRoute = new Route();
        for (Route route: routeList) {
            if (route.getId() == id && route.getContractor().equals(username)){
                route.setSalePrice(salePrice);
                foundRoute = route;
            }

        }
        //If it didn't find a route
        if(foundRoute.getContractor() == null){
            return false;
        }
        createBookingSupplier(foundRoute);
        return true;
    }

    @Override
    public List<Route> getRoutes(){
        return routeRepository.findAll();
    }

    @Override
    public void setRoutes(){
        List<Route> routeList = new ArrayList<>();

        routeList.addAll(routeExternal.getRoutesFromSupplier1());
        routeList.addAll(routeExternal.getRoutesFromSupplier2());
        routeList.addAll(routeExternal.getRoutesFromSupplier3());

        for (Route route:routeList) {
            createBookingSupplier(route);
        }
    }

    @Override
    public boolean createBookingSupplier(Route route){

        if (route == null)
        {
            return false;
        }
        routeRepository.save(route);
        return true;
    }
}
