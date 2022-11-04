package io.codelex.flightplanner.testapi;

import org.springframework.stereotype.Service;

@Service
public class TestService {

    private final TestRepository repository;

    public TestService(TestRepository repository) {
        this.repository = repository;
    }

    public void clearFlight() {
        this.repository.clearFlights();
    }


}
