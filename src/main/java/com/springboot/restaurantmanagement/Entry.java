package com.springboot.restaurantmanagement;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Entry {
    @GetMapping
    public String entry(){
        return "Welcome to Restaurant Management System, go to /docs.html for documentation";
    }
}
