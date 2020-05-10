package com.civelek.Ticket.Controller;

import com.civelek.Ticket.Entity.Company;
import com.civelek.Ticket.Entity.Customer;
import com.civelek.Ticket.Entity.Flight;
import com.civelek.Ticket.Entity.Ticket;
import com.civelek.Ticket.IService.ITicketImpl;
import com.civelek.Ticket.util.VTUtil;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private ITicketImpl iTicketImpl;

    @PostMapping("/saveTicket")
    public JSONObject saveTicket(@Valid @RequestBody Ticket ticket){

        JSONObject sendJson = new JSONObject();

        sendJson = iTicketImpl.saveTicket(ticket);

        return sendJson;

    }


    @GetMapping("/getTicket")
    @ResponseBody
    public void getTicketByPnrNo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pnr = VTUtil.reqGetString(request.getParameter("pnr"),null);
        Boolean status = VTUtil.reqGetBoolean(request.getParameter("status"),null);
        JSONObject sendJson = new JSONObject();
        Ticket ticket = iTicketImpl.getTicketByPnrNo(pnr, status);

        sendJson.put("ticket",ticket);
        response.getWriter().write(sendJson.toString());
    }


    @DeleteMapping("/deleteTicket")
    public ResponseEntity<Boolean> deleteTicket(@RequestParam  String pnr ){
        return ResponseEntity.ok(iTicketImpl.deleteTicket(pnr));
    }


}
