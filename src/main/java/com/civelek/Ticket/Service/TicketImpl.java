package com.civelek.Ticket.Service;

import com.civelek.Ticket.Entity.Flight;
import com.civelek.Ticket.Entity.Ticket;
import com.civelek.Ticket.IService.ITicketImpl;
import com.civelek.Ticket.Repository.FlightRepository;
import com.civelek.Ticket.Repository.TicketDAO;
import com.civelek.Ticket.Repository.TicketRepository;
import com.civelek.Ticket.util.VTUtil;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class TicketImpl implements ITicketImpl {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private TicketDAO ticketDAO;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject saveTicket(Ticket ticket) {

        Ticket newTicket = new Ticket();
        Flight flight = null;
        JSONObject sendJson = new JSONObject();
        Long quato = 0l;
        String pnr = "";
        Ticket pnrControl = null;
        Boolean isPnr = true;

        try{
            if(ticket == null){
                throw new IllegalArgumentException("Bilet bilgileri boş olamaz");
            }else {

                //kontenjan kontrolu yap.kontenjanı azalt

                if (ticket.getFlight() != null && flightRepository.getByFlightId(ticket.getFlight().getFlightId()) != null) {
                    flight = flightRepository.getByFlightId(ticket.getFlight().getFlightId());
                    quato = flight.getQuota();
                } else {
                    throw new IllegalArgumentException("Ucus bilgilerinizi kontrol ediniz");
                }

                if (quato <= 0) {

                    throw new IllegalArgumentException("Sectiginiz ucus icin bos yer kalmamıstır. Lütfen baska ucus seciniz.");
                }

                while (isPnr) {
                    pnr = "PNR" + createPnr(5);
                    pnrControl = ticketRepository.getByPnrAndStatus(pnr, true);

                    if (pnrControl == null) {
                        isPnr = false;
                    }

                }
                if (pnr != null) {
                    newTicket.setFlight(ticket.getFlight());
                    newTicket.setCustomer(ticket.getCustomer());

                    newTicket.setPnr(pnr);

                    sendJson = payment(ticket, flight);

                    if (sendJson != null && sendJson.get("creditCardNo") != null && !sendJson.isEmpty()) {
                        //Bilet satın alma basarılı ise kontenjan azalt.
                        flight.setQuota(quato - 1);
                        flight.setUpdatedAt(new Date());
                        newTicket.setCreditCard(sendJson.get("creditCardNo").toString());
                        newTicket.setPrice(flight.getPrice());
                        newTicket.setPayment(true);
                        newTicket.setCreatedAt(new Date());
                        newTicket.setStatus(true);
                        flightRepository.save(flight);
                        ticketRepository.save(newTicket);
                    }

                }else{

                    throw  new IllegalArgumentException("Bilet satın alma islmei basarısız. Lutfen saha sonra tekrar deneyiniz");
                }

            }


        }catch (Exception e){
            e.getMessage();
        }
        sendJson.put("PNR numarasi", newTicket.getPnr());
        return sendJson;
    }


    public JSONObject payment(Ticket ticket, Flight flight){

        JSONObject json = new JSONObject();

        String cardNo = ticket.getCreditCard();
        String newCardNo = "";

        if(ticket.getCreditCard() != null){
            newCardNo = VTUtil.replaceText(cardNo);

             newCardNo = VTUtil.convertCreditCardSecurtiy(newCardNo,6,4);
            //newCardNo = newCardNo.substring(0,6) + "******" +newCardNo.substring(12,15);
        }


        String message = newCardNo + " no'lu kredi kartınızdan  "+ flight.getPrice() +" tutarında ödeme alınmıştır.";

        json.put("creditCardNo",newCardNo);
        json.put("message",message);

        return json;
    }


    /**
     * Bilet numarasına gore bilet araması yapar
     * @param pnrNo
     * @return
     */
    @Override
    public Ticket getTicketByPnrNo(String pnrNo){
        Ticket ticket = null;
        if (pnrNo != null && !pnrNo.equalsIgnoreCase("")) {
            ticket = ticketDAO.getTicketByPnrNo(pnrNo);
        }
        return ticket;
    }

    @Override
    public Boolean deleteTicket(String pnr) {

        Ticket ticket = ticketRepository.getByPnrNoAndStatus2(pnr);

         if(ticket != null){

             ticketRepository.delete(ticket);
             return  true;

         }

        return false;
    }

    @Override
    public String getTicketByPnrNoToString(String pnr) {
        String ticket = ticketDAO.getTicketByPnrNoToString(pnr);
        return ticket;
    }

    @Override
    public JSONObject getTicketByPnrNoToJson(String pnr) {
        JSONObject ticket = ticketDAO.getTicketByPnrNoToJson(pnr);
        return ticket;
    }


    public  String createPnr(int counter) {
        StringBuilder builder = new StringBuilder();
        while (counter-- != 0) {
            int character = (int)(Math.random()*ALPHABET.length());
            builder.append(ALPHABET.charAt(character));
        }
        return builder.toString();
    }
}
