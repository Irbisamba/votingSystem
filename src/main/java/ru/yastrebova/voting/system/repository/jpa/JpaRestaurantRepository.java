package ru.yastrebova.voting.system.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.yastrebova.voting.system.model.Restaurant;
import ru.yastrebova.voting.system.model.User;
import ru.yastrebova.voting.system.repository.RestaurantRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaRestaurantRepository implements RestaurantRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {
        if (restaurant.isNew()) {
            em.persist(restaurant);
            return restaurant;
        } else {
            return em.merge(restaurant);
        }
    }

    @Override
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(User.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public Restaurant get(int id) {
        return em.find(Restaurant.class, id);
    }

    @Override
    public Restaurant get(String name) {
        return em.find(Restaurant.class, name);
    }

    @Override
    public List<Restaurant> getManageable(int userId) {
        return em.createNamedQuery(Restaurant.ALL_SORTED, Restaurant.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Restaurant> getAll() {
        return em.createNamedQuery(User.ALL_SORTED, Restaurant.class).getResultList();
    }
}
