package org.example.user.avatar.repository;

import org.example.datastore.DataStore;

import java.util.UUID;

public class AvatarRepository {
    private final DataStore dataBase;

    public AvatarRepository(DataStore dataBase){
        this.dataBase = dataBase;
    }
    public void deleteAvatar(UUID uuid){
        this.dataBase.deleteAvatar(uuid);
    }
    public void updateAvatar(UUID uuid,byte[] avatar){
        this.dataBase.updateAvatar(uuid,avatar);
    }
    public void createAvatar(UUID uuid, byte[] avatar){
        this.dataBase.createAvatar(uuid,avatar);
    }
    public byte[] getAvatar(UUID uuid){
        return this.dataBase.getAvatar(uuid);
    }

}
