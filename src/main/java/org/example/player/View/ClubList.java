package org.example.player.View;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.example.factories.ModelFunctionFactory;
import org.example.player.entity.Club;
import org.example.player.model.ClubsModel;
import org.example.player.service.ClubService;

@RequestScoped
@Named
public class ClubList {
    private final ClubService service;
    private ClubsModel Clubs;
    private final ModelFunctionFactory factory;
    @Inject
    public ClubList(ClubService service, ModelFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }
    public ClubsModel getClubs() {
        if (Clubs == null) {
            Clubs = factory.clubsToModelFunction().apply(service.findAllClubs());
        }
        return Clubs;
    }
    public String deleteAction(ClubsModel.Club club) {
        System.out.println("KURECZE");
        service.deleteClub(Club.builder().id(club.getId()).build());
        return "club_list?faces-redirect=true";
    }
}
