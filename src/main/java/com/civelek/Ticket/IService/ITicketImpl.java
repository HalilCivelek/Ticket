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

    /**
     * PNrt numarasına göre bileti siler. Su anda kaydı tamamen siliyor.
     * İstege göre kayıt pasife cekilerek de yapılabilirdi.Fakat o updanin aynısı olduug için delete yazıldı.
     * @param pnr
     * @return
     */
    Boolean deleteTicket(String pnr);
}
