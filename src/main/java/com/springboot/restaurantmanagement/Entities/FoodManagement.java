package com.springboot.restaurantmanagement.Entities;

import com.springboot.restaurantmanagement.Enums.FoodCategory;
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
@Table(name = "food_management")
public class FoodManagement extends Menu{
    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private FoodCategory category;

}