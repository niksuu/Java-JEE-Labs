package org.example.user.avatar.service;

import org.example.user.avatar.repository.AvatarRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;


@ApplicationScoped
@NoArgsConstructor(force = true)
public class AvatarService {
    private final AvatarRepository avatarRepository;

    @Inject
    public AvatarService(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }


    public void updateAvatar(UUID id, InputStream avatar) throws IOException {
        this.avatarRepository.updateAvatar(id, avatar.readAllBytes());
    }

    public void deleteAvatar(UUID uuid) {
        this.avatarRepository.deleteAvatar(uuid);
    }

    public void createAvatar(UUID uuid, byte[] avatar) {
        this.avatarRepository.createAvatar(uuid, avatar);
    }

    public byte[] getAvatar(UUID uuid) {
        return this.avatarRepository.getAvatar(uuid);
    }
}