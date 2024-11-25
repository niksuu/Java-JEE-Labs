package org.example.config.observer;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.context.control.RequestContextController;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.NoArgsConstructor;
import org.example.player.entity.Club;
import org.example.player.entity.Player;
import org.example.player.entity.Role;
import org.example.player.service.ClubService;
import org.example.player.service.PlayerService;
import org.example.user.entity.User;
import org.example.user.service.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;


@ApplicationScoped
@NoArgsConstructor(force = true)
public class DataInitialization  implements ServletContextListener {

    private final UserService userService;
    private final ClubService clubService;
    private final PlayerService playerService;


    @Inject
    public DataInitialization(UserService userService, ClubService clubService, PlayerService playerService) {
        this.userService = userService;
        this.clubService = clubService;
        this.playerService = playerService;
    }

    public void contextInitialized(@Observes @Initialized(ApplicationScoped.class) Object init) {
        init();
    }


    private void init(){

        Club real = Club.builder()
                .id(UUID.randomUUID())
                .name("Real Madrid")
                .description("Real Madrid has won more Spanish top-division (La Liga) championships (36) than any other Spanish side. ")
                .budget(10000000.00)
                .players(new ArrayList<>())
                .build();

        Club barca = Club.builder()
                .id(UUID.randomUUID())
                .name("Fc Barcelona")
                .description("FC Barcelona, Spanish professional football (soccer) club located in Barcelona. FC Barcelona is renowned for its historically skillful and attractive brand of attacking football that places an emphasis on flowing, open play")
                .budget(5500000.00)
                .players(new ArrayList<>())
                .build();

        Club manchesterUnited = Club.builder()
                .id(UUID.randomUUID())
                .name("Manchester United")
                .description("Manchester United, often simply referred to as United, are one of the most iconic and successful football clubs in the world, based in Old Trafford")
                .budget(6500000.00)
                .build();

        Club psg = Club.builder()
                .id(UUID.randomUUID())
                .name("Paris Saint-Germain")
                .description("PSG have the most consecutive seasons playing in France's top flight and are one of two French clubs to have won a major European title.")
                .budget(13500000.00)
                .build();


        if (clubService.findAllClubs().isEmpty()) {
            clubService.createClub(real);
            clubService.createClub(barca);
            clubService.createClub(manchesterUnited);
            clubService.createClub(psg);
        }

        User nikodem = User.builder()
                .id(UUID.randomUUID())
                .username("nikodem")
                .email("wariat123@org")
                .registrationDate(LocalDate.now())
                .build();


        User sebastian = User.builder()
                .id(UUID.randomUUID())
                .username("sebastian")
                .email("seba420@pl")
                .registrationDate(LocalDate.now())
                .build();


        User jakub = User.builder()
                .id(UUID.randomUUID())
                .username("jakub")
                .email("jakub949@pl")
                .registrationDate(LocalDate.now())
                .build();

        User julia = User.builder()
                .id(UUID.randomUUID())
                .username("julia")
                .email("julkakulka@pl")
                .registrationDate(LocalDate.now())
                .build();

        if (userService.findAllUsers().isEmpty()) {
            userService.createUser(nikodem);
            userService.createUser(sebastian);
            userService.createUser(jakub);
            userService.createUser(julia);
        }

        Player ronaldo = Player.builder()
                .id(UUID.randomUUID())
                .name("Cristiano Ronaldo")
                .overall(91)
                .role(Role.STRIKER)
                .club(real)
                .user(sebastian)
                .build();

        Player messi = Player.builder()
                .id(UUID.randomUUID())
                .name("Lionel Messi")
                .overall(93)
                .role(Role.STRIKER)
                .club(barca)
                .user(nikodem)
                .build();


        Player neymar = Player.builder()
                .id(UUID.randomUUID())
                .name("Neymar da Silva")
                .overall(89)
                .role(Role.STRIKER)
                .club(psg)
                .user(nikodem)
                .build();

        Player de_Gea = Player.builder()
                .id(UUID.randomUUID())
                .name("David de Gea")
                .overall(90)
                .role(Role.GOALKEEPER)
                .club(manchesterUnited)
                .user(jakub)
                .build();

        Player bellingham = Player.builder()
                .id(UUID.randomUUID())
                .name("Jude Bellingham")
                .overall(87)
                .role(Role.MIDFIELDER)
                .club(real)
                .user(julia)
                .build();
        if(playerService.findAllPlayers().isEmpty()) {
            playerService.createPlayer(ronaldo);
            playerService.createPlayer(messi);
            playerService.createPlayer(neymar);
            playerService.createPlayer(de_Gea);
            playerService.createPlayer(bellingham);
        }



    //   System.out.println();
    //   System.out.println("================CLUBS======================");
    //   clubService.findAllClubs().forEach(club -> {
    //       System.out.println();
    //       System.out.println("Club: " + club.getName());
    //           System.out.println("Players: :");
    //           club.getPlayers().forEach(player -> {
    //               System.out.println("   * " + player.getName()+" ("+player.getOverall()+") "+player.getRole());
    //           });
    //       System.out.println();
    //       System.out.println(" ");
    //   });
    //   System.out.println();
    //   System.out.println("==================USERS====================");
    //   userService.findAllUsers().forEach(user -> {
    //       System.out.println("User: " + user.getUsername());
    //           System.out.println("Players: :");
    //           user.getPlayers().forEach(player -> {
    //               System.out.println("   * " + player.getName()+" ("+player.getOverall()+") "+player.getRole());
    //           });
    //       System.out.println();
    //       System.out.println(" ");
    //   });
    //   System.out.println();

     //   clubService.deleteClub(barca);
     //   ronaldo.setOverall(90);
     //   playerService.updatePlayer(ronaldo);

     //   System.out.println("==================USERS====================");
     //   userService.findAllUsers().forEach(user -> {
     //       System.out.println("User: " + user.getUsername());
     //           System.out.println("Players: :");
     //           user.getPlayers().forEach(player -> {
     //               System.out.println("   * " + player.getName()+" ("+player.getOverall()+") "+player.getRole());
     //           });
     //       System.out.println();
     //       System.out.println(" ");
     //   });
     //   System.out.println();



    }
}
