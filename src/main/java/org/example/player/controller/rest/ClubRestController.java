package org.example.player.controller.rest;
import jakarta.ejb.EJB;
import jakarta.ejb.EJBException;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.example.factories.DtoFunctionFactory;
import org.example.player.controller.api.ClubController;
import org.example.player.entity.Club;
import org.example.player.model.dto.GetClubResponse;
import org.example.player.model.dto.GetClubsResponse;
import org.example.player.model.dto.PutClubRequest;
import org.example.player.service.ClubService;
import java.util.UUID;
import java.util.logging.Level;


@Path("")
@Log
public class ClubRestController implements ClubController {
    private  ClubService clubService;
    private final DtoFunctionFactory factory;

    @Inject
    public ClubRestController( DtoFunctionFactory factory) {
        this.factory = factory;
    }
    @EJB
    public void setClubService(ClubService clubService) {
        this.clubService = clubService;
    }

    @Override
    public GetClubResponse getClub(UUID uuid) {
        return clubService.findClubById(uuid)
                .map(factory.clubToResponse())
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public GetClubsResponse getClubs() {
        return factory.clubsToResponse().apply(clubService.findAllClubs());
    }

    @Override
    @SneakyThrows
    public void putClubs(UUID uuid, PutClubRequest request) {
        try {
            clubService.createClub(factory.requestToClub().apply(uuid, request));
            throw new WebApplicationException(Response.Status.CREATED);
        } catch (EJBException ex) {
            if (ex.getCause() instanceof IllegalArgumentException) {
                log.log(Level.WARNING, ex.getMessage(), ex);
                throw new BadRequestException(ex);
            }
            throw ex;
        }
    }

    @Override
    public void deleteClub(UUID uuid) {
        clubService.deleteClub(Club.builder().id(uuid).build());
    }

}
