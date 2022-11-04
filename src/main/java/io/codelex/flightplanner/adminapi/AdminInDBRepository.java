package io.codelex.flightplanner.adminapi;

import io.codelex.flightplanner.domain.Flight;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

@Repository
@ConditionalOnProperty(prefix = "flightplanner", name = "connection", havingValue = "database")
public class AdminInDBRepository implements AdminRepository {


    @Override
    public String canConnected() {
        return null;
    }

    @Override
    public Flight getFlight(int flight) {
        return null;
    }

    @Override
    public Flight addFlight(Flight input) {
        return null;
    }

    @Override
    public void removeFlight(int id) {

    }
}
