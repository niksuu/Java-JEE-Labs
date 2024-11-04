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
import java.util.UUID;


@ApplicationScoped
@NoArgsConstructor(force = true)
public class DataInitialization  implements ServletContextListener {

    private final UserService userService;
    private final ClubService clubService;
    private final PlayerService playerService;

    private final RequestContextController requestContextController;


    @Inject
    public DataInitialization(UserService userService, ClubService clubService, PlayerService playerService, RequestContextController requestContextController) {
        this.userService = userService;
        this.clubService = clubService;
        this.playerService = playerService;
        this.requestContextController = requestContextController;
    }

    public void contextInitialized(@Observes @Initialized(ApplicationScoped.class) Object init) {
        init();
    }


    private void init(){
        requestContextController.activate();

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

        Club real = Club.builder()
                .id(UUID.fromString("ce3b7596-948f-4097-a7ef-0b4876afc695"))
                .name("Real Madrid")
                .description("Real Madrid has won more Spanish top-division (La Liga) championships (36) than any other Spanish side. ")
                .budget(10000000.00)
                .build();

        Club barca = Club.builder()
                .id(UUID.fromString("dd56efbf-2998-4b7c-b986-8df98f32fea0"))
                .name("Fc Barcelona")
                .description("FC Barcelona, Spanish professional football (soccer) club located in Barcelona. FC Barcelona is renowned for its historically skillful and attractive brand of attacking football that places an emphasis on flowing, open play")
                .budget(5500000.00)
                .build();

        Club manchesterUnited = Club.builder()
                .id(UUID.fromString("14b58e3a-3917-4e75-a5ce-0e6f7d9f484f"))
                .name("Manchester United")
                .description("Manchester United, often simply referred to as United, are one of the most iconic and successful football clubs in the world, based in Old Trafford")
                .budget(6500000.00)
                .build();

        Club psg = Club.builder()
                .id(UUID.fromString("69f6d05b-a49d-450e-9d04-8c0295a2a3d3"))
                .name("Paris Saint-Germain")
                .description("PSG have the most consecutive seasons playing in France's top flight and are one of two French clubs to have won a major European title.")
                .budget(13500000.00)
                .build();

        clubService.createClub(real);
        clubService.createClub(barca);
        clubService.createClub(manchesterUnited);
        clubService.createClub(psg);


        System.out.println();
        System.out.println();
        System.out.println("======Clubs========:");
        clubService.findAllClubs().forEach(System.out::println);
        System.out.println();
        System.out.println();
        System.out.println();


        Player ronaldo = Player.builder()
                .id(UUID.fromString("b8db2e5a-02fc-4dad-8628-73d4fce10b63"))
                .name("Cristiano Ronaldo")
                .overall(91)
                .role(Role.STRIKER)
                .club(real)
                .user(sebastian)
                .build();

        Player messi = Player.builder()
                .id(UUID.fromString("5abec608-172b-40bc-a742-5fd2e89f347c"))
                .name("Lionel Messi")
                .overall(93)
                .role(Role.STRIKER)
                .club(barca)
                .user(nikodem)
                .build();


        Player neymar = Player.builder()
                .id(UUID.fromString("729a4ab7-3911-4af9-abf0-b25a6f9fdca3"))
                .name("Neymar da Silva")
                .overall(89)
                .role(Role.STRIKER)
                .club(psg)
                .user(nikodem)
                .build();

        Player de_Gea = Player.builder()
                .id(UUID.fromString("b8ba959f-b3b5-4467-a93d-5b514a97de37"))
                .name("David de Gea")
                .overall(90)
                .role(Role.GOALKEEPER)
                .club(manchesterUnited)
                .user(jakub)
                .build();

        Player bellingham = Player.builder()
                .id(UUID.fromString("f7905b93-11b8-4677-aae2-db9a45fbaf2a"))
                .name("Jude Bellingham")
                .overall(87)
                .role(Role.MIDFIELDER)
                .club(real)
                .user(julia)
                .build();

        playerService.createPlayer(ronaldo);
        playerService.createPlayer(messi);
        playerService.createPlayer(neymar);
        playerService.createPlayer(de_Gea);
        playerService.createPlayer(bellingham);




        System.out.println();
        System.out.println("================CLUBS======================");
        clubService.findAllClubs().forEach(club -> {
            System.out.println();
            System.out.println("Club: " + club.getName());
                System.out.println("Players: :");
                club.getPlayers().forEach(player -> {
                    System.out.println("   * " + player.getName()+" ("+player.getOverall()+") "+player.getRole());
                });
            System.out.println();
            System.out.println(" ");
        });
        System.out.println();
        System.out.println("==================USERS====================");
        userService.findAllUsers().forEach(user -> {
            System.out.println("User: " + user.getUsername());
                System.out.println("Players: :");
                user.getPlayers().forEach(player -> {
                    System.out.println("   * " + player.getName()+" ("+player.getOverall()+") "+player.getRole());
                });
            System.out.println();
            System.out.println(" ");
        });
        System.out.println();

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


        requestContextController.deactivate();



    }
}
