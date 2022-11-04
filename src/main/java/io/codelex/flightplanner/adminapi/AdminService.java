package io.codelex.flightplanner.adminapi;

import io.codelex.flightplanner.domain.Flight;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository repository;

    public AdminService(AdminRepository repository) {
        this.repository = repository;
    }

    public String canConnect() {
        return this.repository.canConnected();
    }

    public Flight addFlight(Flight input) {
        return this.repository.addFlight(input);
    }

    public Flight getFlight(int flight) {
        return this.repository.getFlight(flight);
    }

    public void removeFlight(int id) {
        this.repository.removeFlight(id);
    }


}
