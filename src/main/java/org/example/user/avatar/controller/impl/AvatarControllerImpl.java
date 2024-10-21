package org.example.user.avatar.controller.impl;

import org.example.user.avatar.controller.api.AvatarController;
import org.example.user.avatar.service.AvatarService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RequestScoped
public class AvatarControllerImpl implements AvatarController {
    private final AvatarService avatarService;

    @Inject
    public AvatarControllerImpl(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @Override
    public byte[] getAvatar(UUID uuid) {
        return this.avatarService.getAvatar(uuid);
    }

    @Override
    public void deleteAvatar(UUID uuid) {
        this.avatarService.deleteAvatar(uuid);
    }

    @Override
    public void updateAvatar(UUID uuid, InputStream avatar) {
        try {
            this.avatarService.updateAvatar(uuid,avatar);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createAvatar(UUID uuid, byte[] avatar) {
        this.avatarService.createAvatar(uuid,avatar);
    }
}
