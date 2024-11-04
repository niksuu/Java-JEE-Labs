package org.example.player.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.example.datastore.DataStore;
import org.example.player.entity.Player;
import org.example.repository.api.Repository;

@ApplicationScoped
public class PlayerRepositoryImpl implements PlayerRepository {
    private final DataStore dataStore;

    @Inject
    public PlayerRepositoryImpl(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public Optional<Player> find(UUID id) {
        return Optional.ofNullable(dataStore.findPlayerById(id));
    }
    @Override
    public List<Player> findAll() {
        return dataStore.findAllPlayers();
    }
    @Override
    public void create(Player entity) {
        dataStore.createPlayer(entity);
    }
    @Override
    public void delete(Player entity) {
        dataStore.deletePlayer(entity);
    }
    @Override
    public void update(Player entity) {
        dataStore.updatePlayer(entity);
    }

    @Override
    public List<Player> findAllByClub(UUID uuid) {
        return dataStore.findAllByClub(uuid);
    }


}
