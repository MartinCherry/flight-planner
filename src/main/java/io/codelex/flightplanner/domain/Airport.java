package io.codelex.flightplanner.domain;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "airports")
public class Airport {

    @NotBlank
    @NotNull
    private String country;

    @NotBlank
    @NotNull
    private String city;

    @NotBlank
    @NotNull
    @Id
    private String airport;


    public Airport() {
    }

    public Airport(String country, String city, String airport) {
        this.country = country;
        this.city = city;
        this.airport = airport;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Airport airport1)) {
            return false;
        }
        return getCountry().trim().equalsIgnoreCase(airport1.getCountry().trim()) &&
                getCity().trim().equalsIgnoreCase(airport1.getCity().trim()) &&
                getAirport().trim().equalsIgnoreCase(airport1.getAirport().trim());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCountry(), getCity(), getAirport());
    }
}
