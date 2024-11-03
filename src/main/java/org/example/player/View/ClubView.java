package org.example.player.View;


import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;
import lombok.*;
import org.example.factories.ModelFunctionFactory;
import org.example.player.entity.Club;
import org.example.player.entity.Player;
import org.example.player.model.ClubModel;
import org.example.player.service.ClubService;
import org.example.player.service.PlayerService;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

@ViewScoped
@Named
public class ClubView implements Serializable {
    private final ClubService service;
    private final ModelFunctionFactory factory;
    private final PlayerService playerService;

    @Setter
    @Getter
    private UUID id;

    @Getter
    private ClubModel club;

    @Inject
    public ClubView(ClubService service, ModelFunctionFactory factory, PlayerService playerService) {
        this.service = service;
        this.factory = factory;
        this.playerService = playerService;
    }

    public void init() throws IOException {
        Optional<Club> club = service.findClubById(id);
        if (club.isPresent()) {
            this.club = factory.clubToModelFunction().apply(club.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Club not found");
        }
    }

    public String deletePlayer(UUID id){
        playerService.deletePlayer(Player.builder().id(id).build());
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true&includeViewParams=true";
    }
    
}
