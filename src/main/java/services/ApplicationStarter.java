package services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class }, scanBasePackages = {"controllers","entities"})
public class ApplicationStarter {
    public static void main(String[] args) {
//        System.setProperty("server.servlet.context-path",
//                "/SimpleLibrarySpring");


//        Menu menu = new Menu();
//        menu.menu(factory, session);
        SpringApplication.run(ApplicationStarter.class, args);
        System.out.println("services.ApplicationStarter has started");
    }
}