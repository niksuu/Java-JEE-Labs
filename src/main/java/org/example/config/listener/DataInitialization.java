package org.example.config.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.example.user.entity.User;
import org.example.user.service.UserService;

import java.time.LocalDate;
import java.util.UUID;


@WebListener
public class DataInitialization  implements ServletContextListener {

    private UserService userService;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        userService = (UserService) event.getServletContext().getAttribute("userService");
        init();
    }


    private void init(){


        User nikodem = User.builder()
                .id(UUID.fromString("3c9cc7b4-a33b-409d-8aed-ae0ecc24b399"))
                .username("nikodem")
                .email("wariat123@org")
                .registrationDate(LocalDate.now())
                .build();


        User sebastian = User.builder()
                .id(UUID.fromString("153dde34-043c-49aa-84e8-99ac37ecc1e2"))
                .username("sebastian")
                .email("seba420@pl")
                .registrationDate(LocalDate.now())
                .build();


        User jakub = User.builder()
                .id(UUID.fromString("9f000860-2afb-4564-8616-7cf7aecabe4a"))
                .username("jakub")
                .email("jakub949@pl")
                .registrationDate(LocalDate.now())
                .build();

        User julia = User.builder()
                .id(UUID.fromString("5d7eb2a9-0303-423b-9ba7-53d29f4be795"))
                .username("julia")
                .email("julkakulka@pl")
                .registrationDate(LocalDate.now())
                .build();


        userService.createUser(nikodem);
        userService.createUser(sebastian);
        userService.createUser(jakub);
        userService.createUser(julia);

    }
}
