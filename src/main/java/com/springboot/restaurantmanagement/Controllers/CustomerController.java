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
@RequestMapping("/customer")
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
    @GetMapping("/get")
    public ResponseEntity<Customer> getCustomer(@RequestParam(required = false) Long id, @RequestParam(required = false) String name) {
        if (id != null) {
            return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
        } else if (name != null && !name.isEmpty()) {
            return new ResponseEntity<>(service.getByName(name), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    @Operation(
            summary = "this route is used for getting all customers"
    )
    public ResponseEntity<List<Customer>> getAll(){
        List<Customer> customers = service.getAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    @GetMapping("/get/date")
    @Operation(
            summary = "this route is used for getting customers at a given period of time"
    )
    public ResponseEntity<List<Customer>> getAllBy(@RequestParam String from, @RequestParam String to){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime start = LocalDate.parse(from, formatter).atStartOfDay();
        LocalDateTime end = LocalDate.parse(to, formatter).atTime(LocalTime.MAX);
        List<Customer> customers = service.getAllBy(start, end);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PutMapping()
    @Operation(summary = "Update a customer by id or name")
    public ResponseEntity<Customer> updateCustomer(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name,
            @RequestBody CustomerDto customer) {

        Customer updatedCustomer = null;

        if (id != null) {
            updatedCustomer = service.update(id, customer);
        } else if (name != null) {
            updatedCustomer = service.updateByName(name, customer);
        }

        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping
    @Operation(summary = "Delete a customer by id or name")
    public ResponseEntity<String> deleteCustomer(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name) {

        if (id != null) {
            service.deleteById(id);
            return ResponseEntity.ok("Customer with id " + id + " deleted successfully.");
        } else if (name != null) {
            service.deleteByName(name);
            return ResponseEntity.ok("Customer with name '" + name + "' deleted successfully.");
        } else {
            return ResponseEntity.badRequest().body("Please provide either id or name to delete a customer.");
        }
    }

    @DeleteMapping("/remove")
    @Operation(
            summary = "this route is used to delete entire customers"
    )
    public ResponseEntity<?> removeAll(){
        service.deleteAll();
        return new ResponseEntity<>("All customers are successfully deleted",HttpStatus.OK);
    }

}
