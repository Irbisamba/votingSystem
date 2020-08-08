package ru.yastrebova.voting.system.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = Restaurant.ALL, query = "SELECT m FROM Restaurant m"),
        @NamedQuery(name = Restaurant.ALL_SORTED, query = "SELECT m FROM Restaurant m WHERE m.admin.id=:userId"),
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant m WHERE m.id=:id"),
//        @NamedQuery(name = Meal.UPDATE, query = "UPDATE Meal m SET m.dateTime = :datetime, m.calories= :calories," +
//                "m.description=:desc where m.id=:id and m.user.id=:userId")
})
@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "restaurants_unique_name_idx")})
public class Restaurant extends BaseEntity{
    public static final String ALL = "Restaurant.getAll";
    public static final String ALL_SORTED = "Restaurant.getManageable";
    public static final String DELETE = "Restaurant.delete";

    @Column(name = "address")
    private String address;

    @Column(name = "date_of_last_updating")
    private LocalDate dateOfLastUpdating;

    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @CollectionTable(name = "meals", joinColumns = @JoinColumn(name = "restaurant_id"))
    @Column(name = "meal")
    @ElementCollection(fetch = FetchType.EAGER)
    private Map<String, Integer> menuOfTheDay;

    @Column(name = "rating")
    private int rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User admin;

    public Restaurant() {
    }

    public Restaurant(String name) {
        super(name);
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Restaurant)) return false;
        if (!super.equals(o)) return false;
        Restaurant that = (Restaurant) o;
        return getAddress().equals(that.getAddress()) &&
                getAdmin().equals(that.getAdmin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAddress(), getAdmin());
    }
}
