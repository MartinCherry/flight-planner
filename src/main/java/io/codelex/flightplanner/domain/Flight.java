package io.codelex.flightplanner.domain;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Flight {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Valid
    @NotNull
    private Airport from;
    @Valid
    @NotNull
    private Airport to;
    @NotNull
    @NotBlank
    private String carrier;
    @NotNull
    private LocalDateTime departureTime;
    @NotNull
    private LocalDateTime arrivalTime;

    private int id;


    public Flight(AddFlightRequest addFlightRequest) {
        this.from = addFlightRequest.getFrom();
        this.to = addFlightRequest.getTo();
        this.carrier = addFlightRequest.getCarrier();
        this.departureTime = LocalDateTime.parse(addFlightRequest.getDepartureTime(), dateTimeFormatter);
        this.arrivalTime = LocalDateTime.parse(addFlightRequest.getArrivalTime(), dateTimeFormatter);
        this.id = 0;
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

    public String getDepartureTime() {
        return this.departureTime.format(dateTimeFormatter);
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = LocalDateTime.parse(departureTime, dateTimeFormatter);
    }

    public String getArrivalTime() {
        return arrivalTime.format(dateTimeFormatter);
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = LocalDateTime.parse(arrivalTime);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime arivalTimeInDateFormat() {
        return this.arrivalTime;
    }

    public LocalDateTime departureTimeInDateFormat() {
        return this.departureTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Flight flight)) {
            return false;
        }
        return Objects.equals(getFrom(), flight.getFrom()) && Objects.equals(getTo(), flight.getTo()) && Objects.equals(getCarrier(), flight.getCarrier()) && Objects.equals(getDepartureTime(), flight.getDepartureTime()) && Objects.equals(getArrivalTime(), flight.getArrivalTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFrom(), getTo(), getCarrier(), getDepartureTime(), getArrivalTime());
    }
}
