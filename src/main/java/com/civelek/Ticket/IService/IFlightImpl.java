package com.civelek.Ticket.IService;

import com.civelek.Ticket.Entity.Company;
import com.civelek.Ticket.Entity.Flight;
import com.civelek.Ticket.Entity.Route;
import net.minidev.json.JSONObject;

import java.util.Date;

public interface IFlightImpl {

    Flight saveFlight(Flight flight);

    Flight getFlightByFlightId(Long FlightId);

    /**
     * İlgili tarihlerder ucus var mı?
     * @return
     */
    Flight getFlight(Date departaureDate, Date arrivalDate);


    /**
     * Kontenjan artırımı yapıldıgında bilet fiyatı guncellemesi
     * @param flightId
     * @param quota
     * @return
     */
    Flight updateFlight(Long flightId, Long quota);
}
