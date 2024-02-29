package com.example.publictransport.controller;

import com.example.publictransport.model.Route;
import com.example.publictransport.model.RouteObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/public-transport")
public class TransportController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{start}/{end}")
    public List<Route> getBusRoutes(@PathVariable String start, @PathVariable String end){
        RouteObj routeList = restTemplate.getForObject(
                "https://transport-routes.azurewebsites.net/api/v1/route/"
                        + start + " bus" + "/" + end + " bus", RouteObj.class);

        return routeList.getRoutes();
    }

    @GetMapping("/{end}")
    public List<Route> getBusRoutesFromStation(@PathVariable String end){
        RouteObj routeList = restTemplate.getForObject(
                "https://transport-routes.azurewebsites.net/api/v1/route/end/"
                        + end + " bus", RouteObj.class);

        return routeList.getRoutes();
    }

    @GetMapping("/save/{id}")
    public Route saveRuote(@PathVariable Long id){
        Route route = restTemplate.getForObject(
                "https://transport-routes.azurewebsites.net/api/v1/route/save/"
                        + id, Route.class);

        return route;
    }
}
