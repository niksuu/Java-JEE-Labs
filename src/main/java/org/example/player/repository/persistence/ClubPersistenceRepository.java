package org.example.player.repository.persistence;

import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.club.Club;
import org.example.player.repository.api.ClubRepository;

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
//        return em.createQuery("select p from Club p", Club.class).getResultList();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Club> query = cb.createQuery(Club.class);
        Root<Club> root = query.from(Club.class);
        query.select(root);
        return em.createQuery(query).getResultList();
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
