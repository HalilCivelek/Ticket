package com.civelek.Ticket.IService;

import com.civelek.Ticket.Entity.Airport;
import net.minidev.json.JSONObject;

public interface IAirportImpl {

    /**
     * Havaalanlarını kaydeder
     * @param airport
     * @return
     */
    Airport saveAirport(Airport airport);


    /**
     * Havalanı listesini doner
     * @return
     */
    JSONObject getAirportList();

    /**
     * Girilen isme göre havaalanı bilgilerini dondurur
     * @param airportName
     * @return
     */
    Airport getAirport(String airportName);
}
