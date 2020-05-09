package com.civelek.Ticket.Service;

import com.civelek.Ticket.Entity.Company;
import com.civelek.Ticket.Entity.Flight;
import com.civelek.Ticket.IService.IFlightImpl;
import com.civelek.Ticket.Repository.FlightRepository;
import com.civelek.Ticket.Repository.TicketDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

            /*Calendar calendar = Calendar.getInstance();
            calendar.setTime(flight.getArrivalDate());
            calendar.add(5,Cale);
            Date date = flight.getArrivalDate();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss a");
            SimpleDateFormat format = DATE_FORMAT;
            String string = format.format(date);*/
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
    public Flight getFlight(Date departureDate, Date arrivalDate) {
        Flight flight = null;
        if (departureDate != null &&  arrivalDate != null) {
            flight = ticketDAO.getFlight(departureDate, arrivalDate);
        }

        return flight;
    }
}
