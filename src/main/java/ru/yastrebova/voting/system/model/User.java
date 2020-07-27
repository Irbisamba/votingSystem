package ru.yastrebova.voting.system.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class User extends BaseUser {
    private Restaurant favoriteRestaurant;
    private final LocalTime finishVoting = LocalTime.of(11, 00, 00);
    private LocalDate lastVotingDate;

    public void vote(Restaurant restaurant) {
        if (LocalDate.now().equals(lastVotingDate)) {
            if (LocalTime.now().isBefore(finishVoting)) {
                int rating = restaurant.getRating() + 1;
                restaurant.setRating(rating);
                restaurant.getVotedUsers().add(this);
                int changeMindRating = favoriteRestaurant.getRating() - 1;
                favoriteRestaurant.setRating(changeMindRating);
                favoriteRestaurant.getVotedUsers().remove(this);
            }
        } else {
            int rating = restaurant.getRating() + 1;
            restaurant.setRating(rating);
            restaurant.getVotedUsers().add(this);
            lastVotingDate = LocalDate.now();
            favoriteRestaurant = restaurant;
        }
    }
}
