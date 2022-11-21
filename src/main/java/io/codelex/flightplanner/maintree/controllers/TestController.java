package io.codelex.flightplanner.maintree.controllers;

import io.codelex.flightplanner.maintree.services.FlightService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testing-api")
public class TestController {

    private final FlightService service;

    public TestController(FlightService service) {
        this.service = service;
    }

    @PostMapping("/clear")
    public synchronized void clearFlights() {
        this.service.clearFlight();
    }

}
