package io.codelex.flightplanner.adminapi;


import io.codelex.flightplanner.domain.Flight;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin-api")
@Validated
public class AdminController {

    private final AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }

    @RequestMapping("/connect")
    public String canConnect() {
        return this.service.canConnect();
    }


    @PutMapping("/flights")
    @ResponseStatus(HttpStatus.CREATED)
    public synchronized Flight addFlight(@RequestBody @Valid Flight input) {
        return this.service.addFlight(input);
    }

    @GetMapping("/flights/{id}")
    public Flight getFlight(@PathVariable int id) {
        return service.getFlight(id);
    }


    @DeleteMapping("/flights/{id}")
    public void removeFlight(@PathVariable int id) {
        this.service.removeFlight(id);
    }

}




