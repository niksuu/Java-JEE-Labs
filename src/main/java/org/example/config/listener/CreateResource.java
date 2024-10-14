package org.example.config.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.SneakyThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

@WebListener
public class CreateResource implements ServletContextListener {
    @Override
    @SneakyThrows
    public void contextInitialized(ServletContextEvent event) {
        Path avatarDirectory = Paths.get(getClass().getClassLoader().getResource("avatar").toURI());
        event.getServletContext().setAttribute("avatarDirectory", avatarDirectory);
    }
}