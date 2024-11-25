package org.example.player.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import lombok.NoArgsConstructor;
import org.example.player.controller.api.PlayerRepository;
import org.example.player.entity.Club;
import org.example.player.entity.Player;
import org.example.player.controller.api.ClubRepository;
import org.example.user.repository.api.UserRepository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final ClubRepository clubRepository;
    private final UserRepository userRepository;

    @Inject
    public PlayerService(PlayerRepository playerRepository, ClubRepository clubRepository, UserRepository userRepository) {
        this.playerRepository = playerRepository;
        this.clubRepository = clubRepository;
        this.userRepository = userRepository;
    }
    public Optional<Player> findPlayerById(UUID id) {
        return playerRepository.find(id);
    }

    public List<Player> findAllPlayers() {
        return playerRepository.findAll();
    }

    @Transactional
    public void createPlayer(Player player) {
        if (playerRepository.find(player.getId()).isPresent()) {
            throw new IllegalArgumentException("Player already exists.");
        }
        if (clubRepository.find(player.getClub().getId()).isEmpty()) {
            throw new IllegalArgumentException("Club does not exists.");
        }
        playerRepository.create(player);
    }
    @Transactional
    public void deletePlayer(Player player) {
        playerRepository.delete(player);
    }
    @Transactional
    public void updatePlayer(Player player) {
        playerRepository.update(player);
    }
    public List<Player> findAllByClub(Club club){
        return playerRepository.findAllByClub(club);
    }
}
