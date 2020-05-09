package com.civelek.Ticket.Repository;

import com.civelek.Ticket.Entity.Flight;
import com.civelek.Ticket.Entity.Route;
import com.civelek.Ticket.Entity.Ticket;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;

@Repository
public class TicketDAO {

    @PersistenceContext
    protected EntityManager entityManager;

    public EntityManager getEntityManager(){
        return entityManager;
    }

    public Ticket getTicketByPnrNo(String pnr){

        StringBuilder hql = new StringBuilder();

        hql.append("select t as pnr from Ticket t ");
        hql.append(" where t.pnr =:pnrNo ");

        Query query = entityManager.createQuery(hql.toString());

        query.setParameter("pnrNo", pnr);

        return (Ticket) query.getSingleResult();
    }


    public String getTicketByPnrNoToString(String pnr){

        StringBuilder hql = new StringBuilder();

        hql.append("select t.customer.name as name from Ticket t ");
        hql.append(" where t.pnr =:pnrNo ");

        Query query = entityManager.createQuery(hql.toString());

        query.setParameter("pnrNo", pnr);

        return  query.getSingleResult().toString();
    }


    public JSONObject getTicketByPnrNoToJson(String pnr){

        StringBuilder hql = new StringBuilder();
        JSONObject jsonObject =new JSONObject();

        hql.append("select t.customer.name as name from Ticket t ");
        hql.append(" where t.pnr =:pnrNo ");

        Query query = entityManager.createQuery(hql.toString());

        query.setParameter("pnrNo", pnr);

        jsonObject.put("data",query.getSingleResult());

        return  jsonObject;
    }

    public Flight getFlight(Date departureDate, Date arrivalDate){

        StringBuilder hql = new StringBuilder();
        JSONObject jsonObject =new JSONObject();

        hql.append("select f from Flight f  ");
        hql.append(" where f.departureDate = :departureDate ");
        hql.append(" and f.arrivalDate = :arrivalDate");

        Query query = entityManager.createQuery(hql.toString());

        query.setParameter("departureDate", departureDate);
        query.setParameter("arrivalDate", arrivalDate);

        jsonObject.put("data",query.getSingleResult());

        return (Flight) query.getSingleResult();
    }

    /**
     * Rota bilgilerini getirir.
     */
    public Route getRoute(Long routeId, String depertureName, String arrivalName){

        StringBuilder hql = new StringBuilder();
        JSONObject jsonObject =new JSONObject();

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

        jsonObject.put("data",query.getSingleResult());

        return (Route) query.getSingleResult();
    }

}
