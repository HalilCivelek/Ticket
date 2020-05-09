package com.civelek.Ticket.Service;

import com.civelek.Ticket.Entity.Airport;
import com.civelek.Ticket.Entity.Route;
import com.civelek.Ticket.IService.IRouteImpl;
import com.civelek.Ticket.Repository.AirportRepository;
import com.civelek.Ticket.Repository.RouteRepository;
import com.civelek.Ticket.Repository.TicketDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RouteImpl implements IRouteImpl {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private TicketDAO ticketDAO;

    public Route saveRoute( Route route){


        Route newRoute = new Route();
        Airport deperture = new Airport();
        Airport arrival = new Airport();

        if( route == null){

            throw  new IllegalArgumentException("rota belirlerken havaalanı bilgisi bos gecilemez.");
        }else if(route.getArrivalId() != null && route.getDepartureId() != null){

          /*  deperture = airportRepository.getByAirportName(route.getDepartureId().getAirportName());
            arrival = airportRepository.getByAirportName(route.getArrivalId().getAirportName());*/

            if(deperture != null && arrival != null){
                newRoute.setDepartureId(route.getDepartureId());
                newRoute.setArrivalId(route.getArrivalId());
                newRoute.setCreatedAt(new Date());
                newRoute.setStatus(true);

            }else{
                System.out.println("varıs ve kalkıs havalimanları hatalıdır.");
                return null;
            }

        }

           return routeRepository.save(newRoute);

    }

    @Override
    public List<Route> getRoute(Long routeId, String depertureName, String arrivalName) {

        List<Route> route = ticketDAO.getRoute(routeId, depertureName, arrivalName);
        return route;
    }
}
