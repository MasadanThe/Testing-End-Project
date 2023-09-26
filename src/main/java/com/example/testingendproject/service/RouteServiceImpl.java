package com.example.testingendproject.service;

import com.example.testingendproject.model.Account;
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

    @Autowired
    AccountService accountService;



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

        Account foundAccount = accountService.findByUsername(route.getContractor());
        //If it is not your route and you are not a contractor
        if (foundAccount == null){
            return false;
        }
        if (foundAccount.equals(""))
        {
            return false;
        }
        if (!foundAccount.getAccountType().equals("CONTRACTOR"))
        {
            return false;
        }
        routeRepository.save(route);
        return true;

    }
}
