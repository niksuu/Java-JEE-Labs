package org.example.user.controller.api;

import org.example.user.dto.GetUserResponse;
import org.example.user.dto.GetUsersResponse;
import org.example.user.dto.PutUserRequest;
import org.example.user.entity.User;

import java.io.InputStream;
import java.util.UUID;

public interface UserController {
    GetUsersResponse getUsers();
    GetUserResponse getUser(UUID uuid);
    void deleteUser(UUID uuid);
    void updateOrCreateUser(PutUserRequest putUserRequest);
}