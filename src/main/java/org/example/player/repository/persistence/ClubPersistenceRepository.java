package org.example.player.repository.persistence;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.player.controller.api.ClubRepository;
import org.example.player.entity.Club;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Dependent
public class ClubPersistenceRepository implements ClubRepository {
    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Club> find(UUID id) {
        return Optional.ofNullable(em.find(Club.class, id));
    }

    @Override
    public List<Club> findAll() {
        return em.createQuery("select p from Club p", Club.class).getResultList();
    }

    @Override
    public void create(Club entity) {
        em.persist(entity);
    }

    @Override
    public void delete(Club entity) {
        em.remove(em.find(Club.class, entity.getId()));
    }

    @Override
    public void update(Club entity) {
        em.merge(entity);
    }
}