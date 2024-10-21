package org.example.user.avatar.repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.example.datastore.DataStore;
import java.util.UUID;
@ApplicationScoped
public class AvatarRepositoryImpl implements AvatarRepository {
    private final DataStore dataStore;
    @Inject
    public AvatarRepositoryImpl(DataStore dataStore){
        this.dataStore = dataStore;
    }
    public void deleteAvatar(UUID uuid){
        this.dataStore.deleteAvatar(uuid);
    }
    public void updateAvatar(UUID uuid,byte[] avatar){
        this.dataStore.updateAvatar(uuid,avatar);
    }
    public void createAvatar(UUID uuid, byte[] avatar){
        this.dataStore.createAvatar(uuid,avatar);
    }
    public byte[] getAvatar(UUID uuid){
        return this.dataStore.getAvatar(uuid);
    }
}
