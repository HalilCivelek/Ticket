package com.civelek.Ticket.Controller;

import com.civelek.Ticket.Entity.Airport;
import com.civelek.Ticket.IService.IAirportImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/airport")
public class AirportController {

    @Autowired
    private IAirportImpl airportImpl;

    @PostMapping("/saveAirport")
    public ResponseEntity<Airport> saveCustomer(@Valid @RequestBody Airport airport){
        return ResponseEntity.ok(airportImpl.saveAirport(airport));
    }


    @GetMapping("/getAirport")
    @ResponseBody
    public ResponseEntity<Airport> getAirportByAirportName(@RequestParam String airportName){
        Airport airport =  airportImpl.getAirport(airportName);
        return new ResponseEntity<>(airport, HttpStatus.OK);
    }



}
