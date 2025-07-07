package com.springboot.restaurantmanagement.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.springboot.restaurantmanagement.Enums.MenuStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "menu")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String name;
    private double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private MenuStatus status;

    @ManyToOne
    @JoinColumn(name = "restaurant")
    @JsonBackReference
    private Restaurant restaurant;

}