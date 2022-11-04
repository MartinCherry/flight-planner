package io.codelex.flightplanner.customerapi;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.domain.FlightSearch;
import io.codelex.flightplanner.domain.PageResults;
import io.codelex.flightplanner.server.Server;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository
@ConditionalOnProperty(prefix = "flightplanner", name = "connection", havingValue = "inmemory")
public class CustomerInMemoryRepository implements CustomerRepository {

    private final Server server;

    public CustomerInMemoryRepository(Server server) {
        this.server = server;
    }

    public HashSet<Airport> searchAirports(String input) {
        return this.server.searchAirports(input);
    }


    public PageResults<Flight> searchFlights(FlightSearch input) {
        return this.server.searchFlights(input);
    }

    public Flight getFlight(int input) {
        return server.getFlight(input);
    }
}
