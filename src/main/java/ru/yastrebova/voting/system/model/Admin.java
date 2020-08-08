package ru.yastrebova.voting.system.model;

import java.util.Set;

public class Admin extends Usertest {
    private Set<RestaurantTest> restaurants;

    public void createRestaurant(String name, String address) {
        RestaurantTest restaurant = new RestaurantTest(name, this);
        restaurant.setAddress(address);
        restaurants.add(restaurant);
    }

    public void addMeal(String restaurantName, String meal, int price) {
        RestaurantTest restaurant = restaurants.stream()
                .filter(r -> r.getName().equals(restaurantName))
                .findFirst()
                .orElse(new RestaurantTest(restaurantName, this));
        restaurant.getMenuOfTheDay().put(meal, price);
        restaurant.update();
        restaurants.add(restaurant);
    }

    public void deleteMeal(String restaurantName, String meal) {
        RestaurantTest restaurant = restaurants.stream()
                .filter(r -> r.getName().equals(restaurantName))
                .findFirst().
                orElseThrow(() -> new RuntimeException("No such restaurant " + restaurantName));
        restaurant.getMenuOfTheDay().remove(meal);
        restaurant.update();
        restaurants.add(restaurant);
    }

}
