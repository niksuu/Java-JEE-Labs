package org.example.config.singleton;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RunAs;
import jakarta.ejb.*;
import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.example.club.Club;
import org.example.player.entity.Player;
import org.example.player.entity.Role;
import org.example.player.service.ClubService;
import org.example.player.service.PlayerService;
import org.example.user.entity.User;
import org.example.user.entity.UserRoles;
import org.example.user.service.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Singleton
@Startup
@TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
@NoArgsConstructor
@DependsOn("InitializeAdminService")
@DeclareRoles({UserRoles.ADMIN, UserRoles.USER})
@RunAs(UserRoles.ADMIN)
@Log
public class DataInitialization {
    private UserService userService;
    private PlayerService playerService;
    private ClubService clubService;
    @Inject
    private SecurityContext securityContext;

    @EJB
    public void setClubService(ClubService clubService) {
        this.clubService = clubService;
    }

    @EJB
    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

    @EJB
    public void setUserService(UserService userService) {
        this.userService = userService;


    }

    @PostConstruct
    @SneakyThrows
    private void init() {

        User admin = User.builder()
                .id(UUID.randomUUID())
                .login("admin")
                .username("admin")
                .email("admin@simplerpg.example.com")
                .password("adminadmin")
                .registrationDate(LocalDate.now())
                .roles(List.of(UserRoles.ADMIN, UserRoles.USER))
                .build();


        User admin2 = User.builder()
                .id(UUID.randomUUID())
                .login("admin2")
                .username("admin2")
                .email("admin2@simplerpg.example.com")
                .password("adminadmin")
                .registrationDate(LocalDate.now())
                .roles(List.of(UserRoles.ADMIN, UserRoles.USER))
                .build();

        User niko = User.builder()
                .id(UUID.randomUUID())
                .email("example@org")
                .username("niko")
                .login("niko123")
                .password("niko")
                .registrationDate(LocalDate.now())
                .roles(List.of(UserRoles.USER))
                .build();

        User basia = User.builder()
                .id(UUID.randomUUID())
                .email("example@pl")
                .username("basia")
                .login("basia123")
                .password("basia")
                .registrationDate(LocalDate.now())
                .roles(List.of(UserRoles.USER))
                .build();

        User wacek = User.builder()
                .id(UUID.fromString("665e4aba-0640-49c2-b71f-4ddf1f9674ba"))
                .email("example@com")
                .username("wacek")
                .login("wacek123")
                .password("wacek")
                .registrationDate(LocalDate.now())
                .roles(List.of(UserRoles.USER))
                .build();

        User franek = User.builder()
                .id(UUID.randomUUID())
                .email("org@example")
                .username("franek")
                .login("franek123")
                .password("franeczek")
                .registrationDate(LocalDate.now())
                .roles(List.of(UserRoles.USER))
                .build();


        Club barca = Club.builder()
                .id(UUID.randomUUID())
                .name("barca")
                .budget(9000000.00)
                .description("Fc Barcelona club")
                .build();

        Club real= Club.builder()
                .id(UUID.randomUUID())
                .name("real")
                .budget(800000.00)
                .description("Real Madrid club")
                .build();

        Club city = Club.builder()
                .id(UUID.fromString("cdfbd2ad-7c1e-48d7-9f91-2d6d0c089b60"))
                .name("city")
                .budget(700000.00)
                .description("Manchester City club")
                .build();

        Club milan = Club.builder()
                .id(UUID.randomUUID())
                .name("milan")
                .budget(190000.00)
                .description("Ac Milan club")
                .build();


        Player ronaldo = Player.builder()
                .id(UUID.randomUUID())
                .name("Cristiano Ronaldo")
                .overall("91")
                .role(Role.STRIKER)
                .club(real)
                .user(niko)
                .build();

        Player messi = Player.builder()
                .id(UUID.randomUUID())
                .name("Lionel Messi")
                .overall("93")
                .role(Role.STRIKER)
                .club(barca)
                .user(basia)
                .build();


        Player neymar = Player.builder()
                .id(UUID.randomUUID())
                .name("Neymar da Silva")
                .overall("89")
                .role(Role.STRIKER)
                .club(city)
                .user(wacek)
                .build();

        Player de_Gea = Player.builder()
                .id(UUID.randomUUID())
                .name("David de Gea")
                .overall("90")
                .role(Role.GOALKEEPER)
                .club(milan)
                .user(franek)
                .build();

        Player bellingham = Player.builder()
                .id(UUID.randomUUID())
                .name("Jude Bellingham")
                .overall("87")
                .role(Role.MIDFILDER)
                .club(real)
                .user(niko)
                .build();

        if (userService.find("wacek123").isEmpty()){
            userService.createUser(niko);
            userService.createUser(admin);
            userService.createUser(basia);
            userService.createUser(wacek);
            userService.createUser(franek);

            clubService.createClub(real);
            clubService.createClub(barca);
            clubService.createClub(city);
            clubService.createClub(milan);

            playerService.createPlayer(messi);
            playerService.createPlayer(ronaldo);
            playerService.createPlayer(de_Gea);
            playerService.createPlayer(neymar);
            playerService.createPlayer(bellingham);
        }
    }

}
