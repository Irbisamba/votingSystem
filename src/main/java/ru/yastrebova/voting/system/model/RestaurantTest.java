package ru.yastrebova.voting.system.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RestaurantTest {
    private String name;
    private String address;
    private LocalDate dateOfLastUpdating;
    private Map<String, Integer> menuOfTheDay;
    private int rating;
    private Admin admin;

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public List<Usertest> getVotedUsers() {
        return votedUsers;
    }

    public void setVotedUsers(List<Usertest> votedUsers) {
        this.votedUsers = votedUsers;
    }

    private List<Usertest> votedUsers;

    public RestaurantTest(String name, Admin admin) {
        this.name = name;
        this.admin = admin;
        update();
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDateOfLastUpdating() {
        return dateOfLastUpdating;
    }

    public void setDateOfLastUpdating(LocalDate dateOfLastUpdating) {
        this.dateOfLastUpdating = dateOfLastUpdating;
    }

    public Map<String, Integer> getMenuOfTheDay() {
        return menuOfTheDay;
    }

    public void setMenuOfTheDay(Map<String, Integer> menuOfTheDay) {
        this.menuOfTheDay = menuOfTheDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RestaurantTest)) return false;
        RestaurantTest that = (RestaurantTest) o;
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    public void update() {
        if(!dateOfLastUpdating.equals(LocalDate.now())) {
        dateOfLastUpdating = LocalDate.now();
        rating = 0;
        }
    }
}
