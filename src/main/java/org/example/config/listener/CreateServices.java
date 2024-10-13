package org.example.config.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.example.database.DataBase;
import org.example.user.avatar.repository.AvatarRepository;
import org.example.user.avatar.service.AvatarService;
import org.example.user.repository.api.UserRepository;
import org.example.user.repository.memory.UserInMemoryRepository;
import org.example.user.service.UserService;

@WebListener
public class CreateServices implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        DataBase dataBase = (DataBase) event.getServletContext().getAttribute("datasource");

        UserRepository userRepository = new UserInMemoryRepository(dataBase);
        AvatarRepository avatarRepository = new AvatarRepository(dataBase);

        event.getServletContext().setAttribute("userService", new UserService(userRepository));
        event.getServletContext().setAttribute("avatarService", new AvatarService(avatarRepository));
    }
}
