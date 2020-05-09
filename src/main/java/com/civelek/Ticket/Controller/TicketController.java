package com.civelek.Ticket.Controller;

import com.civelek.Ticket.Entity.Company;
import com.civelek.Ticket.Entity.Customer;
import com.civelek.Ticket.Entity.Ticket;
import com.civelek.Ticket.IService.ITicketImpl;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity<Ticket> getTicketByPnrNo(@RequestParam String pnr){
        Ticket ticket =  iTicketImpl.getTicketByPnrNo(pnr);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }


    @DeleteMapping("/deleteTicket")
    public ResponseEntity<Boolean> deleteTicket(@RequestParam  String pnr ){
        return ResponseEntity.ok(iTicketImpl.deleteTicket(pnr));
    }

    @GetMapping("/getTicketString")
    @ResponseBody
    public String getByPnrNoToString(@RequestParam String pnr){
        String ticket =  iTicketImpl.getTicketByPnrNoToString(pnr);
        return ticket;
    }

    @GetMapping("/getTicketJson")
    @ResponseBody
    public JSONObject getTicketByPnrNoToJson(@RequestParam String pnr){
        JSONObject ticket =  iTicketImpl.getTicketByPnrNoToJson(pnr);
        return ticket;
    }

}
