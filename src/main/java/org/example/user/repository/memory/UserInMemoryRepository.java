package org.example.user.repository.memory;

import org.example.datastore.DataStore;
import org.example.user.entity.User;
import org.example.user.repository.api.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserInMemoryRepository implements UserRepository {
    private final DataStore dataBase;

    public UserInMemoryRepository(DataStore dataBase){
        this.dataBase = dataBase;
    }

    @Override
    public Optional<User> find(UUID id) {
        return Optional.ofNullable(dataBase.findUserById(id));
    }

    @Override
    public List<User> findAll() {
        return dataBase.findAllUsers();
    }

    @Override
    public void create(User entity) {
        dataBase.createUser(entity);
    }

    @Override
    public void delete(User entity) {
        dataBase.deleteUser(entity.getId());
    }

    @Override
    public void update(User entity) {
        dataBase.updateUser(entity);
    }
}