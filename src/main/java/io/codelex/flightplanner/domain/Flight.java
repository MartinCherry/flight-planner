package io.codelex.flightplanner.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.codelex.flightplanner.dto.AddFlightRequest;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
@Table(name = "flights")
public class Flight {

    @Transient
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id")
    private int id;

    @Valid
    @NotNull
    @ManyToOne
    @JoinColumn(name = "airport_from")
    private Airport from;

    @Valid
    @NotNull
    @ManyToOne
    @JoinColumn(name = "airport_to")
    private Airport to;

    @NotNull
    @NotBlank
    private String carrier;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime departureTime;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime arrivalTime;

    public Flight() {
    }

    public Flight(AddFlightRequest addFlightRequest) {
        this.from = addFlightRequest.getFrom();
        this.to = addFlightRequest.getTo();
        this.carrier = addFlightRequest.getCarrier();
        this.departureTime = LocalDateTime.parse(addFlightRequest.getDepartureTime(), dateTimeFormatter);
        this.arrivalTime = LocalDateTime.parse(addFlightRequest.getArrivalTime(), dateTimeFormatter);

    }

    public Airport getFrom() {
        return this.from;
    }

    public void setFrom(Airport from) {
        this.from = from;
    }

    public Airport getTo() {
        return this.to;
    }

    public void setTo(Airport to) {
        this.to = to;
    }

    public String getCarrier() {
        return this.carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public LocalDateTime getDepartureTime() {
        return this.departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = LocalDateTime.parse(departureTime, dateTimeFormatter);
    }

    public LocalDateTime getArrivalTime() {
        return this.arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = LocalDateTime.parse(arrivalTime, dateTimeFormatter);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String arivalTimeInString() {
        return this.arrivalTime.format(dateTimeFormatter);
    }

    public String departureTimeInString() {
        return this.departureTime.format(dateTimeFormatter);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Flight flight)) {
            return false;
        }
        return Objects.equals(getFrom(), flight.getFrom()) && Objects.equals(getTo(), flight.getTo()) &&
                Objects.equals(getCarrier(), flight.getCarrier()) &&
                Objects.equals(getDepartureTime(), flight.getDepartureTime()) &&
                Objects.equals(getArrivalTime(), flight.getArrivalTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFrom(), getTo(), getCarrier(), getDepartureTime(), getArrivalTime());
    }
}
