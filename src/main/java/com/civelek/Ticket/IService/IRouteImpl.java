package com.civelek.Ticket.IService;

import com.civelek.Ticket.Entity.Airport;
import com.civelek.Ticket.Entity.Route;
import org.springframework.util.RouteMatcher;

public interface IRouteImpl {

    Route saveRoute( Route route);


    Route getRoute(Long routeId, String depertureName, String arrivalName);
}
