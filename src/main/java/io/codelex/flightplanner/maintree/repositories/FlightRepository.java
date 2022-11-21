package io.codelex.flightplanner.maintree.repositories;import io.codelex.flightplanner.domain.*;import org.springframework.stereotype.Repository;import java.util.HashSet;@Repositorypublic interface FlightRepository {    public Flight getFlight(int input);    public  Flight addFlight(AddFlightRequest input);    public void removeFlight(int id);    public HashSet<Airport> searchAirports(String input);    public PageResults<Flight> searchFlights(FlightSearch input);    public void clearFlights();}