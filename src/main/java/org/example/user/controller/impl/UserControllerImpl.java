package org.example.user.controller.impl;


import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.example.controller.servlet.exception.NotFoundException;
import org.example.factories.DtoFunctionFactory;
import org.example.user.controller.api.UserController;
import org.example.user.dto.GetUserResponse;
import org.example.user.dto.GetUsersResponse;
import org.example.user.dto.PutUserRequest;
import org.example.user.entity.User;
import org.example.user.service.UserService;

import java.util.UUID;


@RequestScoped
public class UserControllerImpl implements UserController {
    private final UserService userService;
    private final DtoFunctionFactory factory;

    @Inject
    public UserControllerImpl(UserService userService, DtoFunctionFactory factory) {
        this.userService = userService;
        this.factory = factory;
    }

    @Override
    public GetUsersResponse getUsers() {
        return factory.usersToResponse().apply(userService.findAllUsers());
    }

    @Override
    public GetUserResponse getUser(UUID uuid) {
        return userService.find(uuid)
                .map(factory.userToResponse())
                .orElseThrow(NotFoundException::new);
    }

    public void deleteUser(UUID uuid) {
        userService.find(uuid)
                .ifPresentOrElse(
                        userService::deleteUser,
                        () -> {
                            throw new NotFoundException();
                        }
                );
    }
    public void updateOrCreateUser(PutUserRequest putUserRequest) {
        User user = factory.requestToUser().apply(putUserRequest);

        if (userService.find(user.getId()).isPresent()){
            userService.updateUser(user);
        }
        else {
            userService.createUser(user);
        }
    }


}
