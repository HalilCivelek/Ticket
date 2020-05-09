package com.civelek.Ticket.Repository;

import com.civelek.Ticket.Entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport,Long> {

    Airport getByAirportId(Long airportId);

    Airport getByAirportName(String airportName);
}
