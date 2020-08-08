package ru.yastrebova.voting.system.repository;

import ru.yastrebova.voting.system.model.Restaurant;

import java.time.LocalDateTime;
import java.util.List;

public interface RestaurantRepository {
    // null if updated meal do not belong to userId
    Restaurant save(Restaurant restaurant);

    // false if meal do not belong to userId
    boolean delete(int id, int userId);

    // null if meal do not belong to userId
    Restaurant get(int id);

    Restaurant get(String name);

    // ORDERED dateTime desc
    List<Restaurant> getManageable(int userId);

    List<Restaurant> getAll();
}
