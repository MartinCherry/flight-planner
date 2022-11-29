--liquibase formatted sql

--changeset martins:2

CREATE TABLE flights
(
    flight_id      SERIAL PRIMARY KEY,
    airport_from   VARCHAR(255) NOT NULL,
    airport_to     VARCHAR(255) NOT NULL,
    carrier        VARCHAR(255) NOT NULL,
    departure_time TIMESTAMP    NOT NULL,
    arrival_time   TIMESTAMP    NOT NULL,
    CONSTRAINT from_airport_fkey FOREIGN KEY (airport_from) REFERENCES airports (airport),
    CONSTRAINT to_airport_fkey FOREIGN KEY (airport_to) REFERENCES airports (airport)
);