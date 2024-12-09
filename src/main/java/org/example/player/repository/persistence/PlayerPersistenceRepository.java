package org.example.player.repository.persistence;

import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.example.club.Club;
import org.example.player.entity.Player;
//import org.example.player.entity.Player_;
import org.example.player.entity.Player_;
import org.example.player.repository.api.PlayerRepository;
import org.example.user.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.persistence.criteria.Root;
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
        //return em.createQuery("select c from Player c", Player.class).getResultList();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Player> query = cb.createQuery(Player.class);
        Root<Player> root = query.from(Player.class);
        query.select(root);
        return em.createQuery(query).getResultList();
    }

    @Override
    public void create(Player entity) {
        em.persist(entity);
        em.refresh(em.find(Club.class, entity.getClub().getId()));
        em.refresh(em.find(User.class, entity.getUser().getId()));
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
//            return Optional.of(em.createQuery("select c from Player c where c.id = :id and c.user = :user", Player.class)
//                    .setParameter("user", user)
//                    .setParameter("id", id)
//                    .getSingleResult());

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Player> query = cb.createQuery(Player.class);
            Root<Player> root = query.from(Player.class);
            query.select(root)
                    .where(cb.and(
                            cb.equal(root.get(Player_.user), user),
                            cb.equal(root.get(Player_.id), id)
                    ));
            return Optional.of(em.createQuery(query).getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<Player> findAllByUser(User user) {
//        return em.createQuery("select c from Player c where c.user = :user", Player.class)
//                .setParameter("user", user)
//                .getResultList();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Player> query = cb.createQuery(Player.class);
        Root<Player> root = query.from(Player.class);
        query.select(root)
                .where(cb.equal(root.get(Player_.user), user));
        return em.createQuery(query).getResultList();
    }

    @Override
    public List<Player> findAllByClub(Club club) {
//        return em.createQuery("select c from Player c where c.club = :club", Player.class)
//                .setParameter("club", club)
//                .getResultList();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Player> query = cb.createQuery(Player.class);
        Root<Player> root = query.from(Player.class);
        query.select(root)
                .where(cb.equal(root.get(Player_.club), club));
        return em.createQuery(query).getResultList();
    }


}
