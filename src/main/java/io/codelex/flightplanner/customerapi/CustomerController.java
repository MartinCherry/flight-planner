package io.codelex.flightplanner.customerapi;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.domain.FlightSearch;
import io.codelex.flightplanner.domain.PageResults;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;

@RestController
@RequestMapping("/api")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/airports")
    public HashSet<Airport> searchAirport(@RequestParam String search) {
        return service.searchAirports(search);
    }

    @PostMapping("/flights/search")
    public PageResults<Flight> searchFlights(@RequestBody @Valid FlightSearch input) {
        return service.searchFlights(input);
    }

    @GetMapping("/flights/{id}")
    public Flight getFlight(@PathVariable int id) {
        return service.getFlight(id);
    }


}
