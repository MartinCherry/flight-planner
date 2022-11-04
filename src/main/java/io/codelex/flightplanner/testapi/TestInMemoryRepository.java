package io.codelex.flightplanner.testapi;

import io.codelex.flightplanner.server.Server;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

@Repository
@ConditionalOnProperty(prefix = "flightplanner", name = "connection", havingValue = "inmemory")
public class TestInMemoryRepository implements TestRepository {

    private final Server server;

    public TestInMemoryRepository(Server server) {
        this.server = server;
    }

    public void clearFlights() {
        server.clearFlights();
    }

}
