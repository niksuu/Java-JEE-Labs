package org.example.datastore;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import org.example.Util.CloningUtility;
import org.example.controller.servlet.exception.NotFoundException;
import org.example.player.entity.Club;
import org.example.player.entity.Player;
import org.example.player.service.PlayerService;
import org.example.user.entity.User;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@ApplicationScoped
@NoArgsConstructor(force = true)
public class DataStore {
    private final Set<User> users = new HashSet<>();
    private final Set<Player> players = new HashSet<>();
    private final Set<Club> clubs = new HashSet<>();

    private final CloningUtility cloningUtility;
    private final Path avatarDirectory;
    private final PlayerService playerService;

    @Inject
    public DataStore(CloningUtility cloningUtility, PlayerService playerService) throws URISyntaxException {
        this.cloningUtility = cloningUtility;
        this.avatarDirectory = Paths.get(getClass().getClassLoader().getResource("avatar").toURI());


        try{
            Files.createDirectories(this.avatarDirectory);
        }catch (IOException e)
        {}
        this.playerService = playerService;
    }

    public synchronized List<User> findAllUsers() {
        return users.stream()
                .map(user -> {
                    User clonedUser = cloningUtility.clone(user);
                    List<Player> userPlayers = players.stream()
                            .filter(player -> player.getUser().getId().equals(clonedUser.getId()))
                            .map(cloningUtility::clone)
                            .collect(Collectors.toList());
                    clonedUser.setPlayers(userPlayers);
                    return clonedUser;
                })
                .collect(Collectors.toList());
    }

    public synchronized User findUserById(UUID uuid) {
        return users.stream()
                .filter(user -> user.getId().equals(uuid))
                .findFirst()
                .map(user -> {
                    User clonedUser = cloningUtility.clone(user);
                    List<Player> userPlayers = players.stream()
                            .filter(player -> player.getUser().getId().equals(clonedUser.getId()))
                            .map(cloningUtility::clone)
                            .collect(Collectors.toList());
                    clonedUser.setPlayers(userPlayers);
                    return clonedUser;
                })
                .orElse(null);
    }

    public synchronized void createUser(User entity) {
        System.out.println("create user");
        if (users.stream().anyMatch(user -> user.getId().equals(entity.getId()))){
            throw new IllegalArgumentException("This id is used!");
        }
        entity.setRegistrationDate(LocalDate.now());
        users.add(cloningUtility.clone(entity));
    }

    public synchronized void deleteUser(UUID id) {
        if (!users.removeIf(user -> user.getId().equals(id))) {
            throw new NotFoundException("There is no user with \"%s\"".formatted(id));
        }
    }

    public synchronized void updateUser(User entity) {
        if (users.removeIf(user -> user.getId().equals(entity.getId()))) {
            users.add(cloningUtility.clone(entity));
        } else {
            throw new IllegalArgumentException("There is no user with \"%s\"".formatted(entity.getId()));
        }
    }
    public synchronized Path getAvatarPath(UUID userId) {
        return avatarDirectory.resolve(userId.toString() + ".png");
    }
    public synchronized void deleteAvatar(UUID uuid) {
        Path avatarPath = getAvatarPath(uuid);
        try {
            if (Files.exists(avatarPath)) {
                Files.delete(avatarPath);
            } else {
                throw new NotFoundException("Avatar for user with id \"%s\" does not exist".formatted(uuid));
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not delete avatar for user with id \"%s\"".formatted(uuid), e);
        }
    }

    public synchronized void updateAvatar(UUID uuid, byte[] avatarData) {
        Path avatarPath = getAvatarPath(uuid);
        try {
            Files.write(avatarPath, avatarData);
        } catch (IOException e) {
            throw new RuntimeException("Could not update avatar for user with id \"%s\"".formatted(uuid), e);
        }
    }

    public synchronized void createAvatar(UUID uuid, byte[] avatarData) {
        Path avatarPath = getAvatarPath(uuid);
        try {
            if (Files.exists(avatarPath)) {
                throw new IllegalArgumentException("Avatar for user with id \"%s\" already exists".formatted(uuid));
            }
            Files.write(avatarPath, avatarData);
        } catch (IOException e) {
            throw new RuntimeException("Could not create avatar for user with id \"%s\"".formatted(uuid), e);
        }
    }

    public synchronized byte[] getAvatar(UUID uuid) {
        Path avatarPath = getAvatarPath(uuid);
        try {
            if (Files.exists(avatarPath)) {
                return Files.readAllBytes(avatarPath);
            } else {
                throw new NotFoundException();
                //throw new IllegalArgumentException("Avatar for user with id \"%s\" does not exist".formatted(uuid));
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not retrieve avatar for user with id \"%s\"".formatted(uuid), e);
        }
    }

    public synchronized Club findClubById(UUID id) {
        return clubs.stream()
                .filter(club -> club.getId().equals(id))
                .findFirst()
                .map(club -> {
                    Club clonedClub = cloningUtility.clone(club);
                    List<Player> clubPlayers = players.stream()
                            .filter(player -> player.getClub().getId().equals(clonedClub.getId()))
                            .map(cloningUtility::clone)
                            .collect(Collectors.toList());
                    clonedClub.setPlayers(clubPlayers);
                    return clonedClub;
                })
                .orElse(null);
    }
    
    
    public synchronized List<Club> findAllClubs() {
        return clubs.stream()
                .map(club -> {
                    Club clonedClub = cloningUtility.clone(club);
                    List<Player> clubPlayers = players.stream()
                            .filter(player -> player.getClub().getId().equals(clonedClub.getId()))
                            .map(cloningUtility::clone)
                            .collect(Collectors.toList());
                    clonedClub.setPlayers(clubPlayers);
                    return clonedClub;
                })
                .collect(Collectors.toList());
    }

    public synchronized void createClub(Club entity) {
        if (clubs.stream().anyMatch(club -> club.getId().equals(entity.getId()))){
            throw new IllegalArgumentException("This id is used!");
        }
        clubs.add(cloningUtility.clone(entity));
    }
    public synchronized void deleteClub(Club entity) {
        if (clubs.removeIf(club -> club.getId().equals(entity.getId()))) {
            for (Player player : playerService.findAllPlayers()) {
                if (player.getClub().getId().equals(entity.getId())) playerService.deletePlayer(player);
            }
        }else {
            throw new IllegalArgumentException("There is no user with \"%s\"".formatted(entity.getId()));

        }
    }
    public synchronized void updateClub(Club entity) {
        if (clubs.removeIf(club -> club.getId().equals(entity.getId()))) {
            clubs.add(cloningUtility.clone(entity));
        } else {
            throw new IllegalArgumentException("There is no user with \"%s\"".formatted(entity.getId()));
        }
    }
    public synchronized Player findPlayerById(UUID id) {
        return players.stream()
                .filter(player -> player.getId().equals(id))
                .findFirst()
                .map(cloningUtility::clone)
                .orElse(null);
    }
    public synchronized List<Player> findAllPlayers() {
        return players.stream()
                .map(cloningUtility::clone)
                .collect(Collectors.toList());
    }
    public synchronized void createPlayer(Player entity) {
        if (players.stream().anyMatch(player -> player.getId().equals(entity.getId()))){
            throw new IllegalArgumentException("This id is used!");
        }
        Player player = cloneWithRelationships(entity);
        players.add(cloningUtility.clone(player));
    }
    public synchronized void deletePlayer(Player entity) {
        if (!players.removeIf(player -> player.getId().equals(entity.getId()))) {
            throw new IllegalArgumentException("There is no user with \"%s\"".formatted(entity.getId()));
        }
    }
    public synchronized void updatePlayer(Player entity) {
        Player value = cloneWithRelationships(entity);
        if (players.removeIf(player -> player.getId().equals(entity.getId()))) {
            players.add(value);
        } else {
            throw new IllegalArgumentException("There is no user with \"%s\"".formatted(entity.getId()));
        }
    }

    private Player cloneWithRelationships(Player value) {
        Player entity = cloningUtility.clone(value);

        if (entity.getUser() != null) {
            entity.setUser(users.stream()
                    .filter(user -> user.getId().equals(value.getUser().getId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("No user with id \"%s\".".formatted(value.getUser().getId()))));
        }

        if (entity.getClub() != null) {
            entity.setClub(clubs.stream()
                    .filter(profession -> profession.getId().equals(value.getClub().getId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("No profession with id \"%s\".".formatted(value.getClub().getId()))));
        }

        return entity;
    }

}