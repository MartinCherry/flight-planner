package io.codelex.flightplanner.maintree.controllers;

import io.codelex.flightplanner.domain.AddFlightRequest;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.maintree.services.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin-api")
@Validated
public class AdminController {

    private final FlightService service;

    public AdminController(FlightService service) {
        this.service = service;
    }


    @PutMapping("/flights")
    @ResponseStatus(HttpStatus.CREATED)
    public synchronized Flight addFlight(@RequestBody @Valid AddFlightRequest input) {
        return this.service.addFlight(input);
    }

    @GetMapping("/flights/{id}")
    public synchronized Flight getFlight(@PathVariable int id) {
        return service.getFlight(id);
    }


    @DeleteMapping("/flights/{id}")
    public synchronized void removeFlight(@PathVariable int id) {
        this.service.removeFlight(id);
    }

}




