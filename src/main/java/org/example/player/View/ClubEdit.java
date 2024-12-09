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
import org.example.player.model.ClubEditModel;
import org.example.player.service.ClubService;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

@ViewScoped
@Named
public class ClubEdit implements Serializable {
    private final ModelFunctionFactory factory;
    private ClubService service;
    @Setter
    @Getter
    private UUID id;


    @Getter
    private ClubEditModel club;


    @Inject
    public ClubEdit(ModelFunctionFactory factory) {
        this.factory = factory;
    }

    @EJB
    public void setService(ClubService service) {
        this.service = service;
    }

    public void init() throws IOException {
        Optional<Club> club = service.findClubById(id);
        if (club.isPresent()) {
            this.club = factory.clubToEditModel().apply(club.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Club not found");
        }
    }

    public String saveAction() {
        service.updateClub(factory.updateClub().apply(service.findClubById(id).orElseThrow(), club));
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true&includeViewParams=true";
    }
}
