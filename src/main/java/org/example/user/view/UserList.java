package org.example.user.view;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.extern.java.Log;
import org.example.factories.ModelFunctionFactory;
import org.example.user.entity.User;
import org.example.user.model.UsersModel;
import org.example.user.service.UserService;

@RequestScoped
@Named
@Log
public class UserList {
    private final ModelFunctionFactory factory;
    private final UserService service;
    private UsersModel users;

    @Inject
    public UserList(ModelFunctionFactory factory, UserService service) {
        this.factory = factory;
        this.service = service;
    }

    public UsersModel getUsers() {
        if (users == null) {
            users = factory.usersToModel().apply(service.findAll());
        }
        return users;
    }

    public String deleteAction(UsersModel.User user) {
        service.deleteUser(User.builder().id(user.getId()).build());
        return "user_list?faces-redirect=true";
    }

}
