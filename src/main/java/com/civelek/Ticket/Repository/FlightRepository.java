package com.civelek.Ticket.Repository;

import com.civelek.Ticket.Entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight,Long> {

    Flight getByFlightId(Long id);

   // Flight getByCompanyName(String flightName);
}
