package io.codelex.flightplanner.server;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.domain.FlightSearch;
import io.codelex.flightplanner.domain.PageResults;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@Repository
@ConditionalOnProperty(prefix = "flightplanner", name = "connection", havingValue = "inmemory")
public class Server {

    private static final List<Flight> flightList = new ArrayList<>();

    private int id = 1;

    public synchronized Flight addFlight(Flight input) {
        if (flightList.stream().anyMatch(flight -> flight.equals(input))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        if ((input.getFrom().equals(input.getTo())) || (input.arivalTimeInDateFormat()
                .isBefore(input.departureTimeInDateFormat())) || (input.arivalTimeInDateFormat()
                .equals(input.departureTimeInDateFormat()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        input.setId(id);
        flightList.add(input);
        id++;
        return input;
    }

    public Flight getFlight(int input) {

        return flightList.stream()
                .filter(flight -> flight.getId() == input)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Flight not found"));
    }

    public void clearFlights() {
        flightList.clear();
    }


    public synchronized void removeFlight(int id) {
        flightList.removeIf(flight -> flight.getId() == id);
    }

    public HashSet<Airport> searchAirports(String input) {
        HashSet<Airport> foundAirports = new HashSet<>();

        String inputConverted = input.toLowerCase().trim();
        for (Flight flight : flightList) {
            String fromCountry = flight.getFrom().getCountry().toLowerCase();
            String fromCity = flight.getFrom().getCity().toLowerCase();
            String fromAirport = flight.getFrom().getAirport().toLowerCase();
            String toCountry = flight.getTo().getCountry().toLowerCase();
            String toCity = flight.getTo().getCity().toLowerCase();
            String toAirport = flight.getTo().getAirport().toLowerCase();

            if ((fromCountry.startsWith(inputConverted))
                    || (fromCity.startsWith(inputConverted))
                    || (fromAirport.startsWith(inputConverted))
                    || (toCountry.startsWith(inputConverted))
                    || (toCity.startsWith(inputConverted))
                    || (toAirport.startsWith(inputConverted))) {
                foundAirports.add(flight.getFrom());
            }

        }
        return foundAirports;
    }

    public PageResults<Flight> searchFlights(FlightSearch input) {
        List<Flight> resultsList = new ArrayList<>() {
        };

        if (input.getFrom().equals(input.getTo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        for (Flight flight : flightList) {
            String searchFrom = input.getFrom().trim().toLowerCase();
            String searchTo = input.getTo().trim().toLowerCase();
            LocalDate searchDate = input.searchDateInFormat();
            String fromAirport = flight.getFrom().getAirport().toLowerCase();
            String toAirport = flight.getTo().getAirport().toLowerCase();
            LocalDate departureDate = flight.departureTimeInDateFormat().toLocalDate();

            if (searchFrom.equals(fromAirport)
                    && searchTo.equals(toAirport)
                    && searchDate.equals(departureDate)) {
                resultsList.add(flight);
            }
        }
        return new PageResults<>(0, resultsList.size(), resultsList);
    }

}
