package org.example.player.View;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import org.example.factories.ModelFunctionFactory;
import org.example.player.model.ClubCreateModel;
import org.example.player.service.ClubService;

import java.io.Serializable;
import java.util.UUID;

@Named
@ViewScoped
public class ClubCreate implements Serializable {

    private final ModelFunctionFactory factory;
    private ClubService clubService;
    @Getter
    private ClubCreateModel club;


    @Inject
    public ClubCreate(
            ModelFunctionFactory factory
    ) {
        this.factory = factory;
    }

    @EJB
    public void setClubService(ClubService clubService) {
        this.clubService = clubService;
    }

    public void init() {
        System.out.println("initek");
        club = ClubCreateModel.builder()
                .id(UUID.randomUUID())
                .build();
    }


    public String saveAction() {
        System.out.println("dupa dupaśna");
        System.out.println(club);
        clubService.createClub(factory.modelToClub().apply(club));
        return "/club/club_list.xhtml?faces-redirect=true";
    }


}
