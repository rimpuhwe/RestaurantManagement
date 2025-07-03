package com.springboot.restaurantmanagement.Controllers;

import com.springboot.restaurantmanagement.DTOs.CustomerDto;
import com.springboot.restaurantmanagement.Entities.Customer;
import com.springboot.restaurantmanagement.Services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
@Tag(name = "Customer API", description = "this api  composed by routes for customer management")

public class CustomerController {
    private final CustomerService service;

    @PostMapping
    @Operation(
            summary = "this route is used for saving/create new customer"
    )
    public ResponseEntity<Customer> addNewCustomer(@Valid @RequestBody CustomerDto customer){
        Customer newCustomer = service.save(customer);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    @Operation(
            summary = "Fetches the customer information based on their unique ID."
    )
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
       return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }
    @GetMapping("/search")
    @Operation(
            summary = "Searches for a customer by their name"
    )
    public ResponseEntity<Customer> search(@RequestParam String name) {
        return new ResponseEntity<>(service.getByName(name), HttpStatus.OK);
    }

    @GetMapping
    @Operation(
            summary = "Returns list of all customers"
    )
    public ResponseEntity<List<Customer>> getAll(){
        List<Customer> customers = service.getAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    @GetMapping("/period")
    @Operation(
            summary = "Returns a list of customers created between the given date range."
    )
    public ResponseEntity<List<Customer>> getAllBy(@RequestParam String from, @RequestParam String to){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime start = LocalDate.parse(from, formatter).atStartOfDay();
        LocalDateTime end = LocalDate.parse(to, formatter).atTime(LocalTime.MAX);
        List<Customer> customers = service.getAllBy(start, end);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Updates an existing customer’s information identified by ID."
    )
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerDto customer){
        Customer updatedCustomer = service.update(id, customer);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deletes a customer based on their ID."
    )
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id){
        service.deleteById(id);
        return new ResponseEntity<>("successfully deleted the customer with ID: "+ id,HttpStatus.OK);

    }
    @DeleteMapping
    @Operation(
            summary = "Deletes all customers from the database"
    )
    public ResponseEntity<?> removeAll(){
        service.deleteAll();
        return new ResponseEntity<>("All customers are successfully deleted",HttpStatus.OK);
    }

}
