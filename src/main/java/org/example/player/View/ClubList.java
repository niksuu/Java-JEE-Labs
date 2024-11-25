package org.example.player.View;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.example.factories.ModelFunctionFactory;
import org.example.player.entity.Club;
import org.example.player.model.ClubsModel;
import org.example.player.service.ClubService;

@ApplicationScoped
@Named
public class ClubList {
    private  ClubService service;
    private final ModelFunctionFactory factory;
    private ClubsModel clubs;


    @Inject
    public ClubList(ModelFunctionFactory factory) {
        this.factory = factory;
    }

    @EJB
    public void setService(ClubService service) {
        this.service = service;
    }

    public ClubsModel getClubs() {
        if (clubs == null) {
            clubs = factory.clubsToModelFunction().apply(service.findAllClubs());
        }
        return clubs;
    }

    public String deleteAction(ClubsModel.Club club) {
        service.deleteClub(Club.builder().id(club.getId()).build());
        return "club_list?faces-redirect=true";
    }
}
