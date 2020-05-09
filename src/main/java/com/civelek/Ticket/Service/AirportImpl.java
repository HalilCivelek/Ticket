package com.civelek.Ticket.Service;

import com.civelek.Ticket.Entity.Airport;
import com.civelek.Ticket.Entity.Company;
import com.civelek.Ticket.IService.IAirportImpl;
import com.civelek.Ticket.Repository.AirportRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AirportImpl implements IAirportImpl {

    @Autowired
    private AirportRepository airportRepository;

    @Override
    public Airport saveAirport(Airport airport){

        Airport newAirport = new Airport();

        try{
            if(airport == null){
                throw  new IllegalArgumentException("Havalanı bilgileri bos gecilemez");

            }else if(airport != null && airport.getAirportName() != null ){
                if(airportRepository.getByAirportName(airport.getAirportName()) != null &&
                   airport.getAirportName().equals(airportRepository.getByAirportName(airport.getAirportName()).getAirportName())){
                  System.out.println("Havalanı daha önceden kaydedilmiştir.");
                  return null;
                }

                newAirport.setAirportName(airport.getAirportName());
                newAirport.setCity(airport.getCity());
                newAirport.setCreatedAt(new Date());
                newAirport.setStatus(true);

            }

        }catch (Exception e){
            e.getMessage();
        }
        return airportRepository.save(newAirport);
    }

    @Override
    public JSONObject getAirportList() {
        List<Airport> airports = airportRepository.findAll();
        JSONObject json = new JSONObject();
        json.put("data",airports.size() == 0 ? "" :(airports.get(0).getAirportName()));
        return json;
    }

    @Override
    public Airport getAirport(String airportName){
        Airport airport = null;
        if (airportName != null && !airportName.equalsIgnoreCase("")) {
            airport = airportRepository.getByAirportName(airportName);
        }

        return airport;
    }

}
