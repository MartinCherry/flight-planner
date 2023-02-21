package io.codelex.flightplanner.maintree.services;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.dto.AddFlightRequest;
import io.codelex.flightplanner.dto.FlightSearch;
import io.codelex.flightplanner.dto.PageResults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FlightService {


    Flight addFlight(AddFlightRequest input);

    Flight getFlight(int input);

    void removeFlight(int id);

    List<Airport> searchAirports(String input);

    PageResults<Flight> searchFlights(FlightSearch input);

    void clearFlight();

    void clearAirports();
}
