package org.example.user.dto.function;

import org.example.user.dto.PutUserRequest;
import org.example.user.entity.User;

import java.util.UUID;
import java.util.function.BiFunction;

public class RequestToUserFunction implements BiFunction<UUID, PutUserRequest, User> {
    @Override
    public User apply(UUID id, PutUserRequest putUserRequest) {
        return User.builder()
                .id(id)
                .email(putUserRequest.getEmail())
                .username(putUserRequest.getUsername())
                .login(putUserRequest.getLogin())
                .registrationDate(putUserRequest.getRegistrationDate())
                .password(putUserRequest.getPassword())
                .build();
    }
}
