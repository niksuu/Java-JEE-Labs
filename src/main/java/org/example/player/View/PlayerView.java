package org.example.player.View;
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
import org.example.player.model.PlayerModel;
import org.example.player.service.ClubService;
import org.example.player.service.PlayerService;
import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;
@ViewScoped
@Named
public class PlayerView implements Serializable {
    private final PlayerService service;
    private final ModelFunctionFactory factory;
    @Setter
    @Getter
    private UUID id;
    @Getter
    private PlayerModel player;
    @Inject
    public PlayerView(PlayerService service, ModelFunctionFactory factory ) {
        this.service = service;
        this.factory = factory;
    }
    public void init() throws IOException {
        Optional<Player> player = service.findPlayerById(id);
        if (player.isPresent()) {
            this.player = factory.playerToModelFunction().apply(player.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Player not found");
        }
    }
}
