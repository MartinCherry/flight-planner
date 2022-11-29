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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
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


    public HashSet<Airport> searchAirports(String input) {
        HashSet<Airport> foundAirports = new HashSet<>();
        Iterable<Flight> flightList = this.flightRepository.findAll();

        String inputConverted = input.toLowerCase().trim();
        for (Flight flight : flightList) {
            String fromCountry = flight.getFrom().getCountry().toLowerCase();
            String fromCity = flight.getFrom().getCity().toLowerCase();
            String fromAirport = flight.getFrom().getAirport().toLowerCase();
            String toCountry = flight.getTo().getCountry().toLowerCase();
            String toCity = flight.getTo().getCity().toLowerCase();
            String toAirport = flight.getTo().getAirport().toLowerCase();

            if ((fromCountry.startsWith(inputConverted)) || (fromCity.startsWith(inputConverted)) ||
                    (fromAirport.startsWith(inputConverted)) || (toCountry.startsWith(inputConverted)) ||
                    (toCity.startsWith(inputConverted)) || (toAirport.startsWith(inputConverted))) {
                foundAirports.add(flight.getFrom());
            }

        }
        return foundAirports;
    }


    public PageResults<Flight> searchFlights(FlightSearch input) {
        List<Flight> resultsList = new ArrayList<>() {
        };

        Iterable<Flight> flightList = flightRepository.findAll();

        if (input.getFrom().equals(input.getTo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        for (Flight flight : flightList) {
            String searchFrom = input.getFrom().trim().toLowerCase();
            String searchTo = input.getTo().trim().toLowerCase();
            LocalDate searchDate = input.searchDateInFormat();
            String fromAirport = flight.getFrom().getAirport().toLowerCase();
            String toAirport = flight.getTo().getAirport().toLowerCase();
            LocalDate departureDate = flight.getDepartureTime().toLocalDate();

            if (searchFrom.equals(fromAirport) && searchTo.equals(toAirport) && searchDate.equals(departureDate)) {
                resultsList.add(flight);
            }
        }
        return new PageResults<>(0, resultsList.size(), resultsList);
    }

    public synchronized void clearFlight() {
        this.flightRepository.deleteAll();
    }
}
