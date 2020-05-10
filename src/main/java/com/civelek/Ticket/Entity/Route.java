package com.civelek.Ticket.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@DynamicUpdate
@DynamicInsert
@Entity
public class Route extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="route_id")
    private long routeId;

    @JoinColumn(name="departure_id", nullable = false)
    @ManyToOne(fetch= FetchType.LAZY)
    private Airport departureId;

    @JoinColumn(name="arrival_id", nullable = false)
    @ManyToOne(fetch= FetchType.LAZY)
    private Airport arrivalId;


    public long getRouteId() {
        return routeId;
    }

    public void setRouteId(long routeId) {
        this.routeId = routeId;
    }

    public Airport getDepartureId() {
        return departureId;
    }

    public void setDepartureId(Airport departureId) {
        this.departureId = departureId;
    }

    public Airport getArrivalId() {
        return arrivalId;
    }

    public void setArrivalId(Airport arrivalId) {
        this.arrivalId = arrivalId;
    }
}
