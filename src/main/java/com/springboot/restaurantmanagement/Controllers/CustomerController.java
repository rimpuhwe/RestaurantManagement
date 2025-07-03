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

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
@Tag(name = "Customer API", description = "this api  composed by routes for customer management")

public class CustomerController {
    private final CustomerService service;

    @PostMapping("/add_new")
    @Operation(
            summary = "this route is used for saving/create new customer"
    )
    public ResponseEntity<Customer> addNewCustomer(@Valid @RequestBody CustomerDto customer){
        Customer newCustomer = service.save(customer);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }
    @GetMapping("/get /{id}")
    @Operation(
            summary = "this route is used to get customer using id"
    )
    public ResponseEntity<Customer> getById(@PathVariable long id){
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }
    @GetMapping("/get_all")
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
    public ResponseEntity<List<Customer>> getAllBy(@RequestParam LocalDateTime from, @RequestParam LocalDateTime to){
        List<Customer> customers = service.getAllBy(from, to);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    @GetMapping("/get/name")
    @Operation(
            summary = "this route is used for getting customer searching by name"
    )
    public ResponseEntity<Customer> getByName(@RequestParam String name){
        return  new ResponseEntity<>(service.getByName(name), HttpStatus.OK);
    }

    @PutMapping("/update/")
    @Operation(
            summary = "this route is used to update customer by his/her name"
    )
    public ResponseEntity<Customer> update(@Valid @RequestBody CustomerDto customer , @RequestParam String name){
        Customer updatedCustomer = service.updateByName(name , customer);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    @Operation(
            summary = "this route is used to update customer with the help of id"
    )
    public ResponseEntity<Customer> update(@PathVariable long id, @RequestBody CustomerDto customerDto){
        Customer updatedCustomer = service.update(id, customerDto);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }
    @DeleteMapping("/remove/{id}")
    @Operation(
            summary = "this route is used for deleting  customer by his/her id"
    )
    public ResponseEntity<?> remove(@PathVariable long id){
        service.deleteById(id);
        return new ResponseEntity<>("deleted successfully",HttpStatus.OK);
    }
    @DeleteMapping("/remove/name")
    @Operation(
            summary = "this route is used for deleting customer by his/her name"
    )
    public ResponseEntity<?> removeByName(@RequestParam String name){
        service.deleteByName(name);
        return new ResponseEntity<>(name + "successfully deleted",HttpStatus.OK);
    }
    @DeleteMapping("/remove/all")
    @Operation(
            summary = "this route is used to delete entire customers"
    )
    public ResponseEntity<?> removeAll(){
        service.deleteAll();
        return new ResponseEntity<>("All customers are successfully deleted",HttpStatus.OK);
    }

}
