package org.example.user.repository.api;

import org.example.repository.api.Repository;
import org.example.user.entity.User;
import java.io.InputStream;
import java.util.UUID;

public interface UserRepository extends Repository<User, UUID> {
}