package com.civelek.Ticket.Repository;


import com.civelek.Ticket.Entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Long> {

    Ticket getByTicketId(Long id);

    Ticket getByPnrAndStatus(String pnr, Boolean status);

}
