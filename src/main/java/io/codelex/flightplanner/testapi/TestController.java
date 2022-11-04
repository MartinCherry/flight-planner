package io.codelex.flightplanner.testapi;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testing-api")
public class TestController {

    private final TestService service;

    public TestController(TestService service) {
        this.service = service;
    }

    @PostMapping("/clear")
    public void clearFlights() {
        this.service.clearFlight();
    }

}
