package org.example.player.controller.api;

import org.example.player.entity.Club;
import org.example.player.entity.Player;
import org.example.repository.api.Repository;
import org.example.user.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlayerRepository extends Repository<Player, UUID> {
    Optional<Player> findByIdAndUser(UUID id, User user);
    List<Player> findAllByUser(User user);
    List<Player> findAllByClub(Club club);
}
