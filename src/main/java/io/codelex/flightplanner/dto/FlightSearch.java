package io.codelex.flightplanner.dto;import javax.validation.constraints.NotNull;import java.time.LocalDate;import java.time.format.DateTimeFormatter;import java.util.Objects;public class FlightSearch {    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");    @NotNull    private String from;    @NotNull    private String to;    @NotNull    private LocalDate departureDate;    public FlightSearch(String from, String to, String departureDate) {        this.from = from;        this.to = to;        this.departureDate = LocalDate.parse(departureDate, dateFormatter);    }    public String getFrom() {        return from;    }    public void setFrom(String from) {        this.from = from;    }    public String getTo() {        return to;    }    public void setTo(String to) {        this.to = to;    }    public String getDepartureDate() {        return departureDate.format(dateFormatter);    }    public void setDepartureDate(LocalDate departureDate) {        this.departureDate = departureDate;    }    public LocalDate searchDateInFormat() {        return this.departureDate;    }    @Override    public boolean equals(Object o) {        if (this == o) {            return true;        }        if (!(o instanceof FlightSearch that)) {            return false;        }        return getFrom().equals(that.getFrom()) && getTo().equals(that.getTo()) &&                getDepartureDate().equals(that.getDepartureDate());    }    @Override    public int hashCode() {        return Objects.hash(getFrom(), getTo(), getDepartureDate());    }}