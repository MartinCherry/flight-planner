package io.codelex.flightplanner.maintree.services;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.dto.AddFlightRequest;
import io.codelex.flightplanner.dto.FlightSearch;
import io.codelex.flightplanner.dto.PageResults;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public interface FlightService {


    Flight addFlight(AddFlightRequest input);

    Flight getFlight(int input);

    void removeFlight(int id);

    HashSet<Airport> searchAirports(String input);

    PageResults<Flight> searchFlights(FlightSearch input);

    void clearFlight();
}
