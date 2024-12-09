package org.example.player.service;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJBAccessException;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import lombok.NoArgsConstructor;
import org.example.player.entity.Club;
import org.example.player.entity.Player;
import org.example.player.repository.api.ClubRepository;
import org.example.player.repository.api.PlayerRepository;
import org.example.user.entity.User;
import org.example.user.entity.UserRoles;
import org.example.user.repository.api.UserRepository;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@LocalBean
@Stateless
@NoArgsConstructor(force = true)
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final ClubRepository clubRepository;
    private final UserRepository userRepository;
    private final SecurityContext securityContext;

    @Inject
    public PlayerService(PlayerRepository playerRepository, ClubRepository clubRepository, UserRepository userRepository, @SuppressWarnings("CdiInjectionPointsInspection") SecurityContext securityContext) {
        this.playerRepository = playerRepository;
        this.clubRepository = clubRepository;
        this.userRepository = userRepository;
        this.securityContext = securityContext;
    }

    @RolesAllowed(UserRoles.USER)
    public Optional<Player> findPlayerById(UUID id) {
        return findForCallerPrincipal(id);
    }

    @RolesAllowed(UserRoles.USER)
    public List<Player> findAllPlayers() {
        return findAllForCallerPrincipal();
    }
    @RolesAllowed(UserRoles.USER)
    public List<Player> findAllPlayersByClub(Club club) {
        return findAllByClubForCallerPrincipal(club);
    }

    @RolesAllowed(UserRoles.ADMIN)
    public void createPlayer(Player player) {
        if (playerRepository.find(player.getId()).isPresent()) {
            throw new IllegalArgumentException("Player already exists.");
        }
        if (clubRepository.find(player.getClub().getId()).isEmpty()) {
            throw new IllegalArgumentException("Club does not exists.");
        }
        playerRepository.create(player);
    }

    @RolesAllowed(UserRoles.USER)
    public Optional<Player> find(User user, UUID id) {
        return playerRepository.findByIdAndUser(id, user);
    }

    @RolesAllowed(UserRoles.USER)
    public void deletePlayer(Player player) {
        checkAdminRoleOrOwner(playerRepository.find(player.getId()));
        playerRepository.delete(player);
    }

    @RolesAllowed(UserRoles.USER)
    public void updatePlayer(Player player) {
        checkAdminRoleOrOwner(playerRepository.find(player.getId()));
        playerRepository.update(player);
    }

    @RolesAllowed(UserRoles.USER)
    public List<Player> findAllByClub(Club club) {
        return playerRepository.findAllByClub(club);
    }

    @RolesAllowed(UserRoles.USER)
    public List<Player> findAll(User user) {
        return playerRepository.findAllByUser(user);
    }

    @RolesAllowed(UserRoles.USER)
    public Optional<Player> findForCallerPrincipal(UUID id) {
        if (securityContext.isCallerInRole(UserRoles.ADMIN)) {
            return findPlayerById(id);
        }

        User user = userRepository.findByLogin(securityContext.getCallerPrincipal().getName())
                .orElseThrow(IllegalStateException::new);

        return find(user, id);
    }

    @RolesAllowed(UserRoles.USER)
    public List<Player> findAllForCallerPrincipal() {
        if (securityContext.isCallerInRole(UserRoles.ADMIN)) {
            return playerRepository.findAll();
        }
        User user = userRepository.findByLogin(securityContext.getCallerPrincipal().getName())
                .orElseThrow(IllegalStateException::new);
        return findAll(user);
    }

    @RolesAllowed(UserRoles.USER)
    public List<Player> findAllByClubForCallerPrincipal(Club club) {
        List<Player> playersByClub = playerRepository.findAllByClub(club);
        if (securityContext.isCallerInRole(UserRoles.ADMIN)) {
            return playersByClub;
        }

        User user = userRepository.findByLogin(securityContext.getCallerPrincipal().getName())
                .orElseThrow(() -> new IllegalStateException("Nie znaleziono zalogowanego użytkownika"));

        return playersByClub.stream()
                .filter(player -> player.getUser().getUsername().equals(user.getUsername()))
                .toList();
    }

    @RolesAllowed(UserRoles.USER)
    public void createForCallerPrincipal(Player player) {
        User user = userRepository.findByLogin(securityContext.getCallerPrincipal().getName())
                .orElseThrow(IllegalStateException::new);

        player.setUser(user);
        createPlayer(player);
    }

    private void checkAdminRoleOrOwner(Optional<Player> player) throws EJBAccessException {
        if (securityContext.isCallerInRole(UserRoles.ADMIN)) {
            return;
        }
        if (securityContext.isCallerInRole(UserRoles.USER)
                && player.isPresent()
                && player.get().getUser().getLogin().equals(securityContext.getCallerPrincipal().getName())) {
            return;
        }
        throw new EJBAccessException("Caller not authorized.");
    }
}
