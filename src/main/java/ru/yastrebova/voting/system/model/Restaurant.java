package ru.yastrebova.voting.system.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Map;

public class Restaurant {

    @Column(name = "address")
    private String address;

    @Column(name = "date_of_last_updating")
    private LocalDate dateOfLastUpdating;

    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @CollectionTable(name = "meals", joinColumns = @JoinColumn(name = "restaurant_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Map<String, Integer> menuOfTheDay;

    @Column(name = "rating")
    private int rating;

    private User admin;
}
