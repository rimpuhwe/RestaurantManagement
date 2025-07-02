package com.springboot.restaurantmanagement.Entities;

import com.springboot.restaurantmanagement.Enums.DrinksCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "drinks_management")
public class DrinksManagement extends Menu {
    private int weight;
    private double volume;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private DrinksCategory category;

}