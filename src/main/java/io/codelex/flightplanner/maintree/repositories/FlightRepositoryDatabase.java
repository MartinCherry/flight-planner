package io.codelex.flightplanner.maintree.repositories;

import io.codelex.flightplanner.domain.Flight;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@ConditionalOnProperty(prefix = "flight-planner", name = "connection", havingValue = "database")
public interface FlightRepositoryDatabase extends JpaRepository<Flight, Integer> {


    @Query("SELECT f FROM Flight f WHERE f.from.airport = :from AND f.to.airport = :to AND f.departureTime >= :departureTimeFrom AND f.departureTime < :departureTimeTo ")
    List<Flight> searchFlight(@Param("from") String from,
                              @Param("to") String to,
                              @Param("departureTimeFrom") LocalDateTime departureTimeFrom,
                              @Param("departureTimeTo") LocalDateTime departureTimeTo);

}
