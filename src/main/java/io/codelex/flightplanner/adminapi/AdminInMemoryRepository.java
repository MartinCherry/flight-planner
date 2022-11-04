package io.codelex.flightplanner.adminapi;

import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.server.Server;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

@Repository
@ConditionalOnProperty(prefix = "flightplanner", name = "connection", havingValue = "inmemory")
public class AdminInMemoryRepository implements AdminRepository {

    private final Server server;

    public AdminInMemoryRepository(Server server) {
        this.server = server;
    }

    @Override
    public String canConnected() {
        return "Admin connected";
    }

    @Override
    public Flight getFlight(int flight) {
        return server.getFlight(flight);
    }

    @Override
    public Flight addFlight(Flight input) {
        return this.server.addFlight(input);
    }

    @Override
    public void removeFlight(int id) {
        this.server.removeFlight(id);
    }

}
