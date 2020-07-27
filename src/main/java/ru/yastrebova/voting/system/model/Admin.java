package ru.yastrebova.voting.system.model;

import java.util.Set;

public class Admin extends BaseUser{
    private Set<Restaurant> restaurants;

    public void createRestaurant(String name, String address) {
        Restaurant restaurant = new Restaurant(name, this);
        restaurant.setAddress(address);
        restaurants.add(restaurant);
    }

    public void addMeal(String restaurantName, String meal, int price) {
        Restaurant restaurant = restaurants.stream()
                .filter(r -> r.getName().equals(restaurantName))
                .findFirst()
                .orElse(new Restaurant(restaurantName, this));
        restaurant.getMenuOfTheDay().put(meal, price);
        restaurant.update();
        restaurants.add(restaurant);
    }

    public void deleteMeal(String restaurantName, String meal) {
        Restaurant restaurant = restaurants.stream()
                .filter(r -> r.getName().equals(restaurantName))
                .findFirst().
                orElseThrow(() -> new RuntimeException("No such restaurant " + restaurantName));
        restaurant.getMenuOfTheDay().remove(meal);
        restaurant.update();
        restaurants.add(restaurant);
    }

}
