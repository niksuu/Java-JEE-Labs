package org.example.player.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import lombok.NoArgsConstructor;
import org.example.player.entity.Player;
import org.example.player.repository.ClubRepository;
import org.example.player.repository.PlayerRepository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final ClubRepository clubRepository;

    @Inject
    public PlayerService(PlayerRepository playerRepository,ClubRepository clubRepository) {
        this.playerRepository = playerRepository;
        this.clubRepository = clubRepository;
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
        System.out.println(player.toString());
        playerRepository.update(player);
    }

    public List<Player> findAllByClub(UUID uuid){
        return playerRepository.findAllByClub(uuid);
    }

    public void updatePlayerToClub(Player player, UUID clubId) {
        System.out.println(player.toString());

        clubRepository.find(clubId).ifPresentOrElse(
                club -> {
                    player.setClub(club);
                    playerRepository.create(player);
                },
                () -> {
                    throw new NotFoundException("Club not found");
                }
        );
    }


}
