package org.example.user.controller.impl;

import jakarta.ejb.EJB;
import jakarta.ejb.EJBException;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.example.factories.DtoFunctionFactory;
import org.example.user.controller.api.UserController;
import org.example.user.dto.GetUserResponse;
import org.example.user.dto.GetUsersResponse;
import org.example.user.dto.PutUserRequest;
import org.example.user.service.UserService;

import java.util.UUID;
import java.util.logging.Level;

@Path("")
@Log
public class UserRestController implements UserController {
    private final DtoFunctionFactory factory;
    private UserService userService;

    @Inject
    public UserRestController(DtoFunctionFactory factory) {
        this.factory = factory;
    }

    @EJB
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public GetUserResponse getUser(UUID uuid) {
        return userService.find(uuid).map(factory.userToResponse())
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public GetUsersResponse getUsers() {
        System.out.println("ile");
        System.out.println(userService.findAll().size());
        return factory.usersToResponse().apply(userService.findAll());
    }

    @Override
    @SneakyThrows
    public void putUser(UUID uuid, PutUserRequest request) {
        try {
            userService.createUser(factory.requestToUser().apply(uuid, request));
            throw new WebApplicationException(Response.Status.CREATED);
        } catch (EJBException ex) {
            if (ex.getCause() instanceof IllegalArgumentException) {
                log.log(Level.WARNING, ex.getMessage(), ex);
                throw new BadRequestException(ex);
            }
            throw ex;
        }
    }
}
