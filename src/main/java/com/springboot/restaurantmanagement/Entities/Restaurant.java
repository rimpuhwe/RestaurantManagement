package com.springboot.restaurantmanagement.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.springboot.restaurantmanagement.Enums.RestaurantStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String name;
    private String address;
    private String phone;
    private float rating;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private RestaurantStatus status;

    @OneToMany(mappedBy = "restaurant", orphanRemoval = true)
    @JsonManagedReference
    private List<Menu> menus;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id")
    private List<Customer> customers = new ArrayList<>();

}