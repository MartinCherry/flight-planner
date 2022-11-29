package io.codelex.flightplanner.maintree.repositories;

import io.codelex.flightplanner.domain.Airport;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@ConditionalOnProperty(prefix = "flight-planner", name = "connection", havingValue = "database")
public interface AirportRepository extends JpaRepository<Airport, Integer> {
}
