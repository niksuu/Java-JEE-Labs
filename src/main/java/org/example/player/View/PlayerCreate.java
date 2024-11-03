package org.example.player.View;
import jakarta.enterprise.context.Conversation;
import jakarta.enterprise.context.ConversationScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.example.factories.ModelFunctionFactory;
import org.example.player.entity.Club;
import org.example.player.entity.Role;
import org.example.player.model.ClubCreateModel;
import org.example.player.model.ClubModel;
import org.example.player.model.PlayerCreateModel;
import org.example.player.service.ClubService;
import org.example.player.service.PlayerService;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@ConversationScoped
@Named
@Log
@NoArgsConstructor(force = true)
public class PlayerCreate implements Serializable {
    private final ClubService clubService;
    private final PlayerService playerService;
    private final ModelFunctionFactory factory;

    @Getter
    private PlayerCreateModel player;

    @Getter
    private List<ClubModel> clubs;

    private final Conversation conversation;

    @Inject
    public PlayerCreate(
            ClubService clubService,
            ModelFunctionFactory factory,
            PlayerService playerService,
            Conversation conversation
    ) {
        this.clubService = clubService;
        this.factory = factory;
        this.playerService = playerService;
        this.conversation = conversation;
    }

    public void init() {
        if (conversation.isTransient()) {
            clubs = clubService.findAllClubs().stream()
                    .map(factory.clubToModelFunction())
                    .collect(Collectors.toList());
            player = PlayerCreateModel.builder()
                    .id(UUID.randomUUID())
                    .build();

            conversation.begin();
        }
    }

    public String saveAction() {
        playerService.createPlayer(factory.modelToPlayerFunction().apply(player));
        conversation.end();
        return "/club/club_list.xhtml?faces-redirect=true";
    }

    public List<Role> getRoles() {
        return Arrays.asList(Role.values());
    }


}
