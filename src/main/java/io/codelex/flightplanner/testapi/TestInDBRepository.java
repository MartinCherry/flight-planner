package io.codelex.flightplanner.testapi;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

@Repository
@ConditionalOnProperty(prefix = "flightplanner", name = "connection", havingValue = "database")
public class TestInDBRepository implements TestRepository {


    @Override
    public void clearFlights() {

    }
}
