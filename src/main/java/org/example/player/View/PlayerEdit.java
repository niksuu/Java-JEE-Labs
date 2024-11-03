package org.example.player.View;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import org.example.factories.ModelFunctionFactory;
import org.example.player.entity.Player;
import org.example.player.entity.Role;
import org.example.player.model.PlayerEditModel;
import org.example.player.service.PlayerService;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@ViewScoped
@Named
public class PlayerEdit implements Serializable {
    private final PlayerService service;
    private final ModelFunctionFactory factory;
    @Setter
    @Getter
    private UUID id;
    @Getter
    private PlayerEditModel player;
    @Inject
    public PlayerEdit(PlayerService service, ModelFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    public void init() throws IOException {
        Optional<Player> player = service.findPlayerById(id);
        if (player.isPresent()) {
            this.player = factory.playerToEditModelFunction().apply(player.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "player not found");
        }
    }
    public String saveAction() {
        service.updatePlayer(factory.updatePlayerWithModelFunction().apply(service.findPlayerById(id).orElseThrow(), player));
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true&includeViewParams=true";
    }

    public List<Role> getRoles() {
        return Arrays.asList(Role.values());
    }
}
