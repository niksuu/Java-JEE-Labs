package org.example.user.avatar.repository;

import java.util.UUID;

public interface AvatarRepository {
    void deleteAvatar(UUID uuid);
    void updateAvatar(UUID uuid,byte[] avatar);
    void createAvatar(UUID uuid, byte[] avatar);
    byte[] getAvatar(UUID uuid);
}
