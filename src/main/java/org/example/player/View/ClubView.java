package org.example.player.View;

import jakarta.ejb.EJB;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import org.example.factories.ModelFunctionFactory;
import org.example.player.entity.Club;
import org.example.player.entity.Player;
import org.example.player.model.ClubModel;
import org.example.player.model.PlayersModel;
import org.example.player.service.ClubService;
import org.example.player.service.PlayerService;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

@ViewScoped
@Named
public class ClubView implements Serializable {
    private final ModelFunctionFactory factory;
    private ClubService service;
    private PlayerService playerService;
    @Setter
    @Getter
    private UUID id;

    @Getter
    private ClubModel club;

    private PlayersModel players;

    @Inject
    public ClubView(ModelFunctionFactory factory) {
        this.factory = factory;
    }

    @EJB
    public void setService(ClubService service) {
        this.service = service;
    }

    @EJB
    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

    public void init() throws IOException {
        Optional<Club> club = service.findClubById(id);
        if (club.isPresent()) {
            this.club = factory.clubToModel().apply(club.get());
            this.players = getPlayers();
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Club not found");
        }
    }
    public PlayersModel getPlayers() throws IOException {
        if (players == null) {
            Optional<Club> club = service.findClubById(id);
            if (club.isPresent()) {
                System.out.println("club present");
                System.out.println(club);
                players = factory.playersToModel().apply(playerService.findAllPlayersByClub(club.get()));
                System.out.println("players");
                System.out.println(players);
            } else {
                FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Club not found");
            }
        }
        return players;
    }

    public String deletePlayer(UUID id) {
        playerService.deletePlayer(Player.builder().id(id).build());
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true&includeViewParams=true";
    }
}
