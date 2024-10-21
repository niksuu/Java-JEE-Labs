package org.example.datastore;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import org.example.Util.CloningUtility;
import org.example.controller.servlet.exception.NotFoundException;
import org.example.user.entity.User;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@ApplicationScoped
@NoArgsConstructor(force = true)
public class DataStore {
    private final Set<User> users = new HashSet<>();

    private final CloningUtility cloningUtility;
    private final Path avatarDirectory;

    @Inject
    public DataStore(CloningUtility cloningUtility) throws URISyntaxException {
        this.cloningUtility = cloningUtility;
        this.avatarDirectory = Paths.get(getClass().getClassLoader().getResource("avatar").toURI());


        try{
            Files.createDirectories(this.avatarDirectory);
        }catch (IOException e)
        {}
    }

    public synchronized List<User> findAllUsers(){
        return users.stream()
                .map(cloningUtility::clone)
                .collect(Collectors.toList());
    }
    public synchronized User findUserById(UUID uuid){
        return users.stream()
                .filter(user -> user.getId().equals(uuid))
                .findFirst()
                .map(cloningUtility::clone)
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
}