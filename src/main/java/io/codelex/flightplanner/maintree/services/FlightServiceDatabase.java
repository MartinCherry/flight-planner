package io.codelex.flightplanner.maintree.services;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.dto.AddFlightRequest;
import io.codelex.flightplanner.dto.FlightSearch;
import io.codelex.flightplanner.dto.PageResults;
import io.codelex.flightplanner.maintree.repositories.AirportRepository;
import io.codelex.flightplanner.maintree.repositories.FlightRepositoryDatabase;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

@Service
@ConditionalOnProperty(prefix = "flight-planner", name = "connection", havingValue = "database")
public class FlightServiceDatabase implements FlightService {

    private final FlightRepositoryDatabase flightRepository;
    private final AirportRepository airportRepository;

    public FlightServiceDatabase(FlightRepositoryDatabase flightRepository, AirportRepository airportRepository) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
    }

    public synchronized Flight addFlight(AddFlightRequest input) {


        Airport airportFrom = airportRepository.save(input.getFrom());
        Airport airportTo = airportRepository.save(input.getTo());
        Flight flight = new Flight(input);
        flight.setFrom(airportFrom);
        flight.setTo(airportTo);

        ExampleMatcher flightMatcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withMatcher("airport_from", ignoreCase())
                .withMatcher("airport_to", ignoreCase())
                .withMatcher("carrier", ignoreCase())
                .withMatcher("departure_time", ignoreCase())
                .withMatcher("arrival_time", ignoreCase());
        Example<Flight> example = Example.of(flight, flightMatcher);

        if (flightRepository.exists(example)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        if ((input.getFrom().equals(input.getTo())) || (flight.getArrivalTime().isBefore(flight.getDepartureTime())) ||
                (flight.getArrivalTime().equals(flight.getDepartureTime()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return this.flightRepository.save(flight);

    }


    public synchronized Flight getFlight(int input) {
        return this.flightRepository.findById(input)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Flight not found"));
    }


    public synchronized void removeFlight(int id) {
        Flight flightToDelete = this.flightRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.OK));
        this.flightRepository.delete(flightToDelete);
    }


    public List<Airport> searchAirports(String input) {
        String searchWord = input.trim().toLowerCase();
        return this.airportRepository.searchAirport(searchWord);
    }


    public PageResults<Flight> searchFlights(FlightSearch input) {
        if (input.getFrom().equals(input.getTo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        List<Flight> flights = flightRepository.searchFlight(
                input.getFrom(),
                input.getTo(),
                input.searchDateInFormat().atStartOfDay(),
                input.searchDateInFormat().plusDays(1).atStartOfDay());
        return new PageResults<>(0, flights.size(), flights);
    }

    public synchronized void clearFlight() {
        this.flightRepository.deleteAll();
    }

    public void clearAirports() {
        this.airportRepository.deleteAll();
    }
}
