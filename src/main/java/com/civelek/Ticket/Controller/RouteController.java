package com.civelek.Ticket.Controller;

import com.civelek.Ticket.Entity.Airport;

import com.civelek.Ticket.Entity.Route;
import com.civelek.Ticket.IService.IRouteImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/route")
public class RouteController {

    @Autowired
    private IRouteImpl routeImpl;

    @PostMapping("/saveRoute")
    public ResponseEntity<Route> saveCustomer(@Valid @RequestBody Route route){
        return ResponseEntity.ok(routeImpl.saveRoute(route));

    }
}
