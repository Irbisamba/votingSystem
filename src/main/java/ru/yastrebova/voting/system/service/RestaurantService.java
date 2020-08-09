package ru.yastrebova.voting.system.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.yastrebova.voting.system.model.Restaurant;
import ru.yastrebova.voting.system.repository.RestaurantRepository;

@Service
public class RestaurantService {

    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant create(Restaurant restaurant, Integer userId) {
        Assert.notNull(restaurant, "meal must not be null");
        return repository.save(restaurant);
    }
}
