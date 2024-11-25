package org.example.player.controller.rest;
import jakarta.ejb.EJB;
import jakarta.ejb.EJBException;
import jakarta.inject.Inject;
import jakarta.transaction.TransactionalException;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import lombok.extern.java.Log;
import org.example.factories.DtoFunctionFactory;
import org.example.player.controller.api.PlayerController;
import org.example.player.entity.Club;
import org.example.player.entity.Player;
import org.example.player.model.dto.GetPlayerResponse;
import org.example.player.model.dto.GetPlayersResponse;
import org.example.player.model.dto.PutPlayerRequest;
import org.example.player.service.PlayerService;
import java.util.UUID;
import java.util.logging.Level;

@Path("")
@Log
public class PlayerRestController implements PlayerController {
    private  PlayerService service;
    private final DtoFunctionFactory factory;

    @Inject
    public PlayerRestController( DtoFunctionFactory factory) {
        this.factory = factory;
    }
    @EJB
    public void setService(PlayerService service) {
        this.service = service;
    }

    @Override
    public GetPlayersResponse getPlayers() {
        return factory.playersToResponse().apply(service.findAllPlayers());
    }

    @Override
    public GetPlayersResponse getPlayerOfClub(UUID uuid) {
        return factory.playersToResponse().apply(service.findAllByClub(Club.builder().id(uuid).build()));
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
        try {
            service.createPlayer(factory.requestToPlayer().apply(clubId,id,request));
        }  catch (EJBException ex) {
            if (ex.getCause() instanceof IllegalArgumentException) {
                log.log(Level.WARNING, ex.getMessage(), ex);
                throw new BadRequestException(ex);
            }
            throw ex;
        }
    }

}
