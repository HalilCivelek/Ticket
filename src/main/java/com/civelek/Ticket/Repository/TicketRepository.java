package com.civelek.Ticket.Repository;

import com.civelek.Ticket.Entity.Flight;
import com.civelek.Ticket.Entity.Ticket;
import net.minidev.json.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


public interface TicketRepository extends JpaRepository<Ticket,Long> {

    Ticket getByTicketId(Long id);

    Ticket getByPnrAndStatus(String pnr, Boolean status);

    /**
     * Bilet numarasına göre bilet bilgilerini getirir.
     * @param pnrNo
     * @return
     */
    @Query(value = "select  t.ticketId  from Ticket t where t.pnr = :pnrNo" )
    Ticket getByPnrNoAndStatus2(@Param("pnrNo") String pnrNo);
}
