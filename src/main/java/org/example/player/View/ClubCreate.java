package org.example.player.View;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.Conversation;
import jakarta.enterprise.context.ConversationScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.example.factories.ModelFunctionFactory;
import org.example.player.model.ClubCreateModel;
import org.example.player.service.ClubService;
import org.example.player.service.PlayerService;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Named
@ViewScoped
public class ClubCreate implements Serializable {
    private  ClubService clubService;
    private final ModelFunctionFactory factory;
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
        System.out.println(club);
        clubService.createClub(factory.modelToClubFunction().apply(club));
        return "/club/club_list.xhtml?faces-redirect=true";
    }
}