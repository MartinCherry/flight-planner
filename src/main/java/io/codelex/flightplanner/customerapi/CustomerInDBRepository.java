package io.codelex.flightplanner.customerapi;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.domain.FlightSearch;
import io.codelex.flightplanner.domain.PageResults;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository
@ConditionalOnProperty(prefix = "flightplanner", name = "connection", havingValue = "database")
public class CustomerInDBRepository implements CustomerRepository {


    @Override
    public HashSet<Airport> searchAirports(String input) {
        return null;
    }

    @Override
    public PageResults<Flight> searchFlights(FlightSearch input) {
        return null;
    }

    @Override
    public Flight getFlight(int input) {
        return null;
    }
}
