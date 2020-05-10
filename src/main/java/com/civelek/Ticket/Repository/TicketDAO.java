package com.civelek.Ticket.Repository;

import com.civelek.Ticket.Entity.Customer;
import com.civelek.Ticket.Entity.Flight;
import com.civelek.Ticket.Entity.Route;
import com.civelek.Ticket.Entity.Ticket;
import javassist.NotFoundException;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Repository
public class TicketDAO {

    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    private TicketRepository ticketRepository;


    public EntityManager getEntityManager(){
        return entityManager;
    }

    public Ticket getTicketByPnrNo(String pnr, Boolean status){
  try {
      StringBuilder hql = new StringBuilder();

      hql.append("select t as pnr from Ticket t ");
      hql.append(" where 1 = 1 ");

      if (status != null) {
          hql.append(" and t.status =:status ");
      }

      if (pnr != null) {
          hql.append(" and t.pnr =:pnr ");
      }

      Query query = entityManager.createQuery(hql.toString());

      if (status != null) {
          query.setParameter("status", status);
      }
      if (pnr != null) {
          query.setParameter("pnr", pnr);
      }


      return (Ticket) query.getSingleResult();
  }catch (NoResultException e){
      e.getMessage();
  }
  return  null;
    }

    public List<Flight> getFlight(String company, String departaure, String arrival, Date departureDate, Date arrivalDate){

        StringBuilder hql = new StringBuilder();

        hql.append("select f from Flight f  ");
        hql.append(" where status = true");

        if(arrivalDate != null){
            hql.append(" and f.arrivalDate = :arrivalDate");
        }

        if(departureDate != null){
            hql.append(" and f.departureDate = :departureDate ");
        }

        if(company != null){
           hql.append(" and f.company.companyName =:company");
        }

        if(arrival != null){
            hql.append(" and f.arrival.route.arrivalId.airportName =:arrival");
        }

        if(departaure != null){
            hql.append(" and f.arrival.route.departaureId.airportName =:departaure");
        }


        Query query = entityManager.createQuery(hql.toString());

        if(departureDate != null){
            query.setParameter("departureDate", departureDate);
        }

        if(arrivalDate != null){
            query.setParameter("arrivalDate", arrivalDate);
        }

        if(company != null){
            query.setParameter("company", company);
        }

        if(arrival != null){
            query.setParameter("arrival", arrival);
        }

        if (departaure != null){
            query.setParameter("departaure", departaure);
        }


        return query.getResultList();
    }

    /**
     * Rota bilgilerini getirir.
     */
    public List<Customer> getCustomer(String kimlikNo, String customerName, String customerSurname, String phone){

        StringBuilder hql = new StringBuilder();

        hql.append("select c from Customer c  ");
        hql.append(" where c.status = true ");

        if(kimlikNo != null){
            hql.append(" and c.kimlikNo =:kimlikNo ");
        }
        if(customerName != null){
            hql.append(" and c.name=:customerName");
         }

        if(customerSurname != null){
            hql.append(" and c.surname =:customerSurname");
        }

        if(phone != null){
            hql.append(" and c.phone =:phone");
        }
        Query query = entityManager.createQuery(hql.toString());

        if(kimlikNo != null){
         query.setParameter("kimlikNo", kimlikNo);
        }

        if(customerName != null){
           query.setParameter("customerName",customerName);
        }

        if (customerSurname != null){
            query.setParameter("customerSurname", customerSurname);
        }

        if(phone != null){
            query.setParameter("phone", phone);
        }

        return query.getResultList();
    }

    /**
     * Musteri bilgilerini getirir
     */
    public List<Route> getRoute(Long routeId, String depertureName, String arrivalName){

        StringBuilder hql = new StringBuilder();

        hql.append("select r from Route r  ");
        hql.append(" where r.status = true ");

        if(routeId != null){
            hql.append(" and r.routeId =:routeId ");
        }
        if(depertureName != null){
            hql.append(" and r.departureId.airportName =:depertureName");
        }

        if(arrivalName != null){
            hql.append(" and r.arrivalId.airportName =:arrivalName");
        }
        Query query = entityManager.createQuery(hql.toString());

        if(routeId != null){
            query.setParameter("routeId", routeId);
        }

        if(depertureName != null){
            query.setParameter("depertureName",depertureName);
        }

        if (arrivalName != null){
            query.setParameter("arrivalName", arrivalName);
        }

        return query.getResultList();
    }



}
