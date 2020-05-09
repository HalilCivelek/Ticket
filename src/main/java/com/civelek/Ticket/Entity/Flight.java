package com.civelek.Ticket.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@DynamicUpdate
@DynamicInsert
@Entity
public class Flight extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="flight_id")
    private long flightId;

    @JoinColumn(name="company_id")
    @ManyToOne(fetch= FetchType.LAZY)
    private Company company;

    @Column(name = "quota")
    private  Long quota;


    @JoinColumn(name="route_id")
    @ManyToOne(fetch= FetchType.EAGER)
    private Route route;

    @Column(name = "price")
    private Long price;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "arrival_date")
    private Date arrivalDate;

    @DateTimeFormat(pattern = "dd/mm/yyyy")
    @Column(name = "departure_date")
    private Date departureDate;

    public long getFlightId() {
        return flightId;
    }

    public void setFlightId(long flightId) {
        this.flightId = flightId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Long getQuota() {
        return quota;
    }

    public void setQuota(Long quota) {
        this.quota = quota;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }
}
