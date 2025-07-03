package com.springboot.restaurantmanagement.Services;

import com.springboot.restaurantmanagement.DTOs.CustomerDto;
import com.springboot.restaurantmanagement.Entities.Customer;
import com.springboot.restaurantmanagement.Repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CustomerService {
    private  CustomerRepository customerRepository;


    public Customer save(CustomerDto customerDto){
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhone_number(customerDto.getPhone_number());
        return customerRepository.save(customer);
    }
    public Customer getByName(String name){
        return customerRepository.findByNameContainingIgnoreCase(name);

    }
    public Customer getById(long id) {
        return customerRepository.findById(id).orElseThrow(()-> new RuntimeException("Customer not found"));

    }
    public List<Customer> getAllBy(LocalDateTime from, LocalDateTime to){
        return customerRepository.findByCreatedAtBetween(from, to);
    }
    public List<Customer> getAll(){
        return customerRepository.findAll();
    }
    public Customer updateByName(String name,CustomerDto customerDto){
        var customer = customerRepository.findByName(name);
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhone_number(customerDto.getPhone_number());
        return customerRepository.save(customer);
    }
    public Customer update(long id,CustomerDto customerDto){
        var customer = customerRepository.findById(id).orElseThrow(()-> new RuntimeException("Customer not found"));
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhone_number(customerDto.getPhone_number());
        return customerRepository.save(customer);
    }
    public void deleteById(long id){
        customerRepository.deleteById(id);
    }
    public void deleteByName(String name){
        customerRepository.deleteByName(name);
    }
    public void deleteAll(){
        customerRepository.deleteAll();
    }

}
