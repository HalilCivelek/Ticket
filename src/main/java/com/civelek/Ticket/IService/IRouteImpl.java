package com.civelek.Ticket.IService;


import com.civelek.Ticket.Entity.Route;

import java.util.List;

public interface IRouteImpl {

    Route saveRoute( Route route);


    /**
     * Arama kriterlrine göre rotayı geri döndürür.
     * @param routeId
     * @param depertureName
     * @param arrivalName
     * @return
     */
    List<Route> getRoute(Long routeId, String depertureName, String arrivalName);
}
