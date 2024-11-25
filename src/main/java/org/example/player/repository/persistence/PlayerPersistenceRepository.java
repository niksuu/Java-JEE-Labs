package org.example.player.repository.persistence;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.example.player.controller.api.PlayerRepository;
import org.example.player.entity.Club;
import org.example.player.entity.Player;
import org.example.user.entity.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Dependent
public class PlayerPersistenceRepository implements PlayerRepository {
    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Player> find(UUID id) {
        return Optional.ofNullable(em.find(Player.class, id));
    }

    @Override
    public List<Player> findAll() {
        return em.createQuery("select c from Player c", Player.class).getResultList();
    }

    @Override
    public void create(Player entity) {
        em.persist(entity);
    }

    @Override
    public void delete(Player entity) {
        em.remove(em.find(Player.class, entity.getId()));
    }

    @Override
    public void update(Player entity) {
        em.merge(entity);
    }

    @Override
    public Optional<Player> findByIdAndUser(UUID id, User user) {
        try {
            return Optional.of(em.createQuery("select c from Player c where c.id = :id and c.user = :user", Player.class)
                    .setParameter("user", user)
                    .setParameter("id", id)
                    .getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<Player> findAllByUser(User user) {
        return em.createQuery("select c from Player c where c.user = :user", Player.class)
                .setParameter("user", user)
                .getResultList();
    }

    @Override
    public List<Player> findAllByClub(Club club) {
        return em.createQuery("select c from Player c where c.club = :club", Player.class)
                .setParameter("club", club)
                .getResultList();
    }


}