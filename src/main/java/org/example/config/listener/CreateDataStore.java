package org.example.config.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.example.Util.CloningUtility;
import org.example.datastore.DataStore;

import java.nio.file.Path;

@WebListener
public class CreateDataStore  implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        Path avatarDirectory = (Path) event.getServletContext().getAttribute("avatarDirectory");
        event.getServletContext().setAttribute("datasource",new DataStore(new CloningUtility(),avatarDirectory));
    }
}