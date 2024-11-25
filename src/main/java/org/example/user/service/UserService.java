package org.example.user.service;

import jakarta.transaction.Transactional;
import org.example.user.entity.User;
import org.example.user.repository.api.UserRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@ApplicationScoped
@NoArgsConstructor(force = true)
public class UserService {
    private final UserRepository userRepository;

    @Inject
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> find(UUID uuid) { return userRepository.find(uuid);}
    public List<User> findAllUsers() { return userRepository.findAll();}
    @Transactional
    public void createUser(User user){ userRepository.create(user);}
    public void deleteUser(User user){ userRepository.delete(user);}
    public void updateUser(User user){userRepository.update(user);}

    @Transactional
    public void updateAvatar(UUID id, InputStream is) {
        userRepository.find(id).ifPresent(user -> {
            try {
                user.setAvatar(is.readAllBytes());
                userRepository.update(user);
            } catch (IOException ex) {
                throw new IllegalStateException(ex);
            }
        });
    }
}
