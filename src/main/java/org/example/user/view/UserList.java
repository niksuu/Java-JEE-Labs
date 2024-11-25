package org.example.user.view;

import jakarta.ejb.EJB;
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
    private  UserService service;
    private final ModelFunctionFactory factory;
    private UsersModel users;


    @Inject
    public UserList(ModelFunctionFactory factory) {
        this.factory = factory;
    }
    @EJB
    public void setService(UserService service) {
        this.service = service;
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
