package io.codelex.flightplanner.maintree.controllers;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.dto.FlightSearch;
import io.codelex.flightplanner.dto.PageResults;
import io.codelex.flightplanner.maintree.services.FlightService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    private final FlightService service;

    public CustomerController(FlightService service) {
        this.service = service;
    }

    @GetMapping("/airports")
    public List<Airport> searchAirport(@RequestParam String search) {
        return service.searchAirports(search);
    }

    @PostMapping("/flights/search")
    public PageResults<Flight> searchFlights(@RequestBody @Valid FlightSearch input) {
        return service.searchFlights(input);
    }

    @GetMapping("/flights/{id}")
    public synchronized Flight getFlight(@PathVariable int id) {
        return service.getFlight(id);
    }
}
