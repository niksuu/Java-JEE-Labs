package org.example.user.avatar.repository;

import org.example.database.DataBase;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class AvatarRepository {
    private final DataBase dataBase;

    public AvatarRepository(DataBase dataBase){
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
