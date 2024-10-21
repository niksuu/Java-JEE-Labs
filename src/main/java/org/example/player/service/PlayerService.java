package org.example.player.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import org.example.player.entity.Player;
import org.example.player.repository.PlayerRepository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Inject
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> findAllPlayers() {
        return playerRepository.findAll();
    }

    public Optional<Player> findPlayerById(UUID id) {
        return playerRepository.find(id);
    }

    public void createPlayer(Player player) {
        playerRepository.create(player);
    }
    public void deletePlayer(Player player) {
        playerRepository.delete(player);
    }
    public void updatePlayer(Player player) {
        playerRepository.update(player);
    }


}
