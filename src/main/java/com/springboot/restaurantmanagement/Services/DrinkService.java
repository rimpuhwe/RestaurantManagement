package com.springboot.restaurantmanagement.Services;

import com.springboot.restaurantmanagement.DTOs.DrinkDto;
import com.springboot.restaurantmanagement.DTOs.DrinksUpdateDto;
import com.springboot.restaurantmanagement.Entities.DrinksManagement;
import com.springboot.restaurantmanagement.Enums.DrinksCategory;
import com.springboot.restaurantmanagement.Enums.MenuStatus;
import com.springboot.restaurantmanagement.Repositories.DrinksRepository;
import com.springboot.restaurantmanagement.Repositories.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DrinkService {
    private final DrinksRepository repository;
    private final RestaurantRepository restaurantRepository;

    public DrinksManagement createNew(DrinkDto dto , String name) {
        var restaurant = restaurantRepository.findByNameContainingIgnoreCase(name);
        DrinksManagement drinks = new DrinksManagement();
        drinks.setName(dto.getName());
        drinks.setPrice(dto.getPrice());
        drinks.setCategory(dto.getCategory());
        drinks.setStatus(dto.getStatus());
        drinks.setWeight(dto.getWeight());
        drinks.setVolume(dto.getVolume());
        drinks.setRestaurant(restaurant);
        return  repository.save(drinks);
    }
    public DrinksManagement findById(Long id) {
        return repository.findById(id).orElseThrow(()-> new RuntimeException("could not find  a Drink with id: " + id));
    }
    public List<DrinksManagement> findBy(String name, DrinksCategory category, Double volume, MenuStatus status) {
        List<DrinksManagement> allDrinks = repository.findAll();

        return allDrinks.stream()
                .filter(drink -> name == null || drink.getRestaurant().getName().equalsIgnoreCase(name))
                .filter(drink -> category == null || drink.getCategory() == category)
                .filter(drink -> volume == null || Double.compare(drink.getVolume(), volume) == 0)
                .filter(drink -> status == null || drink.getStatus() == status)
                .collect(Collectors.toList());
    }
    public List<DrinksManagement> findByPrice(Double price1,Double price2) {
        return repository.findAllByPriceBetween(price1 , price2);
    }
    public List<DrinksManagement> findAll() {
        return repository.findAll();
    }
    public DrinksManagement update(DrinksUpdateDto dto, Long id,String name) {
        DrinksManagement drinks = repository.findById(id).orElseThrow(()-> new RuntimeException("could not find  a Drink with id: " + id));
        if(drinks != null && drinks.getRestaurant().getName().equals(name)) {
            drinks.setPrice(dto.getPrice());
            drinks.setWeight(dto.getWeight());
            drinks.setStatus(dto.getStatus());
        }
        else{
            throw new RuntimeException("could not find  a Drink with id: " + id + "in "+ name);
        }

        return repository.save(drinks);

    }
    public void deleteById(Long id){
        repository.deleteById(id);
    }
    public void delete(){
        repository.deleteAll();
    }

}
