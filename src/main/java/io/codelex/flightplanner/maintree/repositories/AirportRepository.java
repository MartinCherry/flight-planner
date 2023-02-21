package io.codelex.flightplanner.maintree.repositories;

import io.codelex.flightplanner.domain.Airport;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@ConditionalOnProperty(prefix = "flight-planner", name = "connection", havingValue = "database")
public interface AirportRepository extends JpaRepository<Airport, Integer> {

    @Query(value = "select a from Airport a where UPPER(a.airport) LIKE UPPER(CONCAT('%', :search, '%')) or UPPER(a.country) LIKE UPPER(CONCAT('%', :search, '%')) or UPPER(a.city) LIKE UPPER(CONCAT('%', :search, '%'))")
    List<Airport> searchAirport(String search);
}
