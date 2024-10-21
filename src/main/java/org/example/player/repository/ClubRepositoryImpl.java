package org.example.player.repository;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.example.datastore.DataStore;
import org.example.player.entity.Club;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class ClubRepositoryImpl implements ClubRepository {
    private final DataStore dataStore;

    @Inject
    public ClubRepositoryImpl(DataStore dataStore) {
        this.dataStore = dataStore;
    }
    
    @Override
    public Optional<Club> find(UUID id) {
        return Optional.ofNullable(dataStore.findClubById(id));
    }
    @Override
    public List<Club> findAll() {
        return dataStore.findAllClubs();
    }
    @Override
    public void create(Club entity) {
        dataStore.createClub(entity);
    }
    @Override
    public void delete(Club entity) {
        dataStore.deleteClub(entity);
    }
    @Override
    public void update(Club entity) {
        dataStore.updateClub(entity);
    }
}
