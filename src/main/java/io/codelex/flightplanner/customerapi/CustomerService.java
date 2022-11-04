package io.codelex.flightplanner.customerapi;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.domain.FlightSearch;
import io.codelex.flightplanner.domain.PageResults;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public HashSet<Airport> searchAirports(String input) {
        return repository.searchAirports(input);
    }

    public PageResults<Flight> searchFlights(FlightSearch input) {
        return repository.searchFlights(input);
    }

    public Flight getFlight(int input) {
        return this.repository.getFlight(input);
    }


}
