package com.civelek.Ticket.Service;

import com.civelek.Ticket.Entity.Flight;
import com.civelek.Ticket.IService.IFlightImpl;
import com.civelek.Ticket.Repository.FlightRepository;
import com.civelek.Ticket.Repository.TicketDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class FlightImpl implements IFlightImpl {

    private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private TicketDAO ticketDAO;

    @Override
    public Flight saveFlight(Flight flight) {

        Flight newFlight = new  Flight();
        try{
        if(flight == null ){
            throw new IllegalArgumentException("Ucus bilgileri boş gecilemez");
        }else{
            // aynı havalimanında 5 dakika içinde ucus var mı?
            newFlight.setCompany(flight.getCompany());
            newFlight.setPrice(flight.getPrice());
            newFlight.setQuota(flight.getQuota());
            newFlight.setRoute(flight.getRoute());
            newFlight.setStatus(true);
            newFlight.setCreatedAt(new Date());

            newFlight.setArrivalDate(flight.getArrivalDate());
            newFlight.setDepartureDate(flight.getDepartureDate());

        }

        }catch (Exception e){
            e.getMessage();
        }
        return flightRepository.save(newFlight);
    }

    @Override
    public Flight getFlightByFlightId(Long flightId) {
        Flight flight = null;
        if (flightId != null ) {
            flight = flightRepository.getByFlightId(flightId);
        }

        return flight;
    }

    @Override
    public List<Flight> getFlight(String company,String departaure, String arrival, Date departureDate, Date arrivalDate) {
        List<Flight> flight = null;

        flight = ticketDAO.getFlight(company, departaure, arrival, departureDate, arrivalDate);

        return flight;
    }

    @Override
    public Flight updateFlight(Long flightId, Long quota) {

        Flight flight = flightRepository.getByFlightId(flightId);

        if(flight != null){
            Long newQuta = (flight.getQuota()*quota)/100 + flight.getQuota();
            flight.setQuota(newQuta);

            Long newPrice = (flight.getPrice()*quota)/100 + flight.getPrice();
            flight.setPrice(newPrice);
            flight.setUpdatedAt(new Date());

            return flightRepository.save(flight);
        }

        System.out.println("Guncellenecek ucus bilgisi bulunamadi.");
        return null;
    }

}
