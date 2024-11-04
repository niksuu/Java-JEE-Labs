package org.example.player.controller.rest;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import org.example.factories.DtoFunctionFactory;
import org.example.player.controller.api.PlayerController;
import org.example.player.entity.Player;
import org.example.player.model.dto.GetPlayerResponse;
import org.example.player.model.dto.GetPlayersResponse;
import org.example.player.model.dto.PutPlayerRequest;
import org.example.player.service.PlayerService;
import java.util.UUID;
@Path("")
public class PlayerRestController implements PlayerController {
    private final PlayerService service;
    private final DtoFunctionFactory factory;
    @Inject
    public PlayerRestController(PlayerService service, DtoFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }
    @Override
    public GetPlayersResponse getPlayers() {
        return factory.playersToResponse().apply(service.findAllPlayers());
    }
    @Override
    public GetPlayersResponse getPlayerOfClub(UUID id) {
        return factory.playersToResponse().apply(service.findAllByClub(id));
    }
    @Override
    public GetPlayerResponse getPlayer(UUID id) {
        return service.findPlayerById(id)
                .map(factory.playerToResponse())
                .orElseThrow(NotFoundException::new);
    }
    @Override
    public void deletePlayer(UUID id) {
        service.deletePlayer(Player.builder().id(id).build());
    }
    @Override
    public void putPlayer(UUID clubId, UUID id, PutPlayerRequest request) {
        System.out.println(request);
        try {
            service.updatePlayerToClub(factory.requestToPlayer().apply(id,request),clubId);
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException(ex);
        }
    }
}
