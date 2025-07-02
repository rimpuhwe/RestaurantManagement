package com.springboot.restaurantmanagement.Controllers;

import com.springboot.restaurantmanagement.DTOs.CustomerDto;
import com.springboot.restaurantmanagement.Entities.Customer;
import com.springboot.restaurantmanagement.Repositories.CustomerRepository;
import com.springboot.restaurantmanagement.Services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService service;

    @PostMapping("/add_new")
    public ResponseEntity<Customer> addNewCustomer(@RequestBody CustomerDto customer){
        Customer newCustomer = service.save(customer);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }
    @GetMapping("/get /{id}")
    public ResponseEntity<Customer> getById(@PathVariable long id){
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }
    @GetMapping("/get_all")
    public ResponseEntity<List<Customer>> getAll(){
        List<Customer> customers = service.getAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    @GetMapping("/get/date")
    public ResponseEntity<List<Customer>> getAllBy(@RequestParam LocalDateTime from, @RequestParam LocalDateTime to){
        List<Customer> customers = service.getAllBy(from, to);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    @GetMapping("/get/name")
    public ResponseEntity<Customer> getByName(@RequestParam String name){
        return  new ResponseEntity<>(service.getByName(name), HttpStatus.OK);
    }

    @PutMapping("/update/")
    public ResponseEntity<Customer> update(@RequestBody CustomerDto customer , @RequestParam String name){
        Customer updatedCustomer = service.updateByName(name , customer);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Customer> update(@PathVariable long id, @RequestBody CustomerDto customerDto){
        Customer updatedCustomer = service.update(id, customerDto);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> remove(@PathVariable long id){
        service.deleteById(id);
        return new ResponseEntity<>("deleted successfully",HttpStatus.OK);
    }
    @DeleteMapping("/remove/name")
    public ResponseEntity<?> removeByName(@RequestParam String name){
        service.deleteByName(name);
        return new ResponseEntity<>(name + "successfully deleted",HttpStatus.OK);
    }
    @DeleteMapping("/remove/all")
    public ResponseEntity<?> removeAll(){
        service.deleteAll();
        return new ResponseEntity<>("All customers are successfully deleted",HttpStatus.OK);
    }

}
