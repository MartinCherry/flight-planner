--liquibase formatted sql

--changeset martins:1

CREATE TABLE airports
(
    airport VARCHAR(255) PRIMARY KEY NOT NULL,
    country VARCHAR(255)             NOT NULL,
    city    VARCHAR(255)             NOT NULL
);
