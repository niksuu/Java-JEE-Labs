package org.example.player.View;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.OptimisticLockException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import org.example.factories.ModelFunctionFactory;
import org.example.player.entity.Player;
import org.example.player.model.PlayerEditModel;
import org.example.player.service.PlayerService;
import org.example.user.entity.User;
import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;
@ViewScoped
@Named
public class PlayerEdit implements Serializable{
    private PlayerService service;
    private final ModelFunctionFactory factory;
    private final FacesContext facesContext;
    @Setter
    @Getter
    private UUID id;
    @Getter
    private PlayerEditModel player;
    @Getter
    private PlayerEditModel unsavedPlayer;
    @Inject
    public PlayerEdit(ModelFunctionFactory factory, FacesContext facesContext) {
        this.factory = factory;
        this.facesContext = facesContext;
    }
    @EJB
    public void setService(PlayerService service) {
        this.service = service;
    }
    public void init() throws IOException {
        Optional<Player> player = service.findForCallerPrincipal(id);
        if (player.isPresent()) {
            this.player = factory.playerToEditModel().apply(player.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "player not found or user is not the owner or the player");
        }
    }
    public String saveAction() throws IOException {
        try {
            service.updatePlayer(factory.updatePlayer().apply(service.findPlayerById(id).orElseThrow(), player));
            String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
            return viewId + "?faces-redirect=true&includeViewParams=true";
        } catch (Exception ex) {
            if (ex.getCause() instanceof OptimisticLockException) {
                unsavedPlayer = player;
                init();
                facesContext.addMessage(null, new FacesMessage("Version collision."));
            }
            return null;
        }
    }
}
