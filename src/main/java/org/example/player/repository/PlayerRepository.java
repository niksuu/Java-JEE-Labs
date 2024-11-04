package org.example.player.repository;

import org.example.player.entity.Player;
import org.example.repository.api.Repository;

import java.util.List;
import java.util.UUID;


public interface PlayerRepository extends Repository<Player, UUID> {
    List<Player> findAllByClub(UUID uuid);
}
