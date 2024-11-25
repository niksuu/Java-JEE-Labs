package org.example.user.view;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.example.factories.ModelFunctionFactory;
import org.example.user.entity.User;
import org.example.user.model.UsersModel;
import org.example.user.service.UserService;

@ApplicationScoped
@Named
public class UserList {
    private final UserService service;
    private UsersModel users;
    private final ModelFunctionFactory factory;


    @Inject
    public UserList(UserService service, ModelFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    public UsersModel getUsers() {
        if (users == null) {
            users = factory.usersToModel().apply(service.findAllUsers());
        }
        return users;
    }

    public String deleteAction(UsersModel.User user) {
        service.deleteUser(User.builder().id(user.getId()).build());
        return "character_list?faces-redirect=true";
    }
}
