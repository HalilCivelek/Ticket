package com.civelek.Ticket.IService;

import com.civelek.Ticket.Entity.Ticket;
import com.sun.org.apache.xpath.internal.operations.Bool;
import net.minidev.json.JSONObject;

public interface ITicketImpl {

    JSONObject saveTicket (Ticket ticket);

    /**
     * Bilet numarasıma gore bilet araması yapar
     * @param pnrNo
     * @return
     */
    Ticket getTicketByPnrNo(String pnrNo, Boolean status);

    Boolean deleteTicket(String pnr);
}
