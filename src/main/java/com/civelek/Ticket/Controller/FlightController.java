package com.civelek.Ticket.Controller;

import com.civelek.Ticket.Entity.Flight;
import com.civelek.Ticket.IService.IFlightImpl;
import com.civelek.Ticket.util.VTUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {

    @Autowired
    private IFlightImpl iFlight;

    @PostMapping("/saveFlight")
    public ResponseEntity<Flight> saveFlight(@Valid @RequestBody Flight flight){
        return ResponseEntity.ok(iFlight.saveFlight(flight));

    }


    @GetMapping("/getFlightByFlightId")
    @ResponseBody
    public void getFlightByFlightId(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Long flightId = VTUtil.reqGetLong(request.getParameter("flightId"),null);
        JSONObject sendJson = new JSONObject();
        Flight flight = iFlight.getFlightByFlightId(flightId);

        sendJson.put("flight",flight);
        response.getWriter().write(sendJson.toString());
    }


    @GetMapping("/getFlight")
    @ResponseBody
    public void getFlight(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String departaure = VTUtil.reqGetString(request.getParameter("departaure"),null);
        String arrival = VTUtil.reqGetString(request.getParameter("arrival"),null);
        String companyName = VTUtil.reqGetString(request.getParameter("companyName"),null);
        Date departaureDate = VTUtil.reqGetDate(request.getParameter("departaureDate"),null,VTUtil.strDateFormatVadegmecum);
        Date arrivalDate = VTUtil.reqGetDate(request.getParameter("arrivalDate"),null,VTUtil.strDateFormatVadegmecum);
        JSONObject sendJson = new JSONObject();
        List<Flight> flight = iFlight.getFlight(companyName, departaure, arrival, departaureDate, arrivalDate );

        sendJson.put("flight",flight);
        response.getWriter().write(sendJson.toString());
    }

    @PostMapping("/updateFlight")
    @ResponseBody
    public void updateFlight(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Long flightId = VTUtil.reqGetLong(request.getParameter("flightId"),null);
        Long quota = VTUtil.reqGetLong(request.getParameter("quota"),null);
        JSONObject sendJson = new JSONObject();
        Flight flight = iFlight.updateFlight(flightId, quota);

        sendJson.put("flight",flight);
        response.getWriter().write(sendJson.toString());

    }
}
