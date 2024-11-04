package org.example.player.controller.rest;
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
import org.example.factories.DtoFunctionFactory;
import org.example.player.controller.api.ClubController;
import org.example.player.entity.Club;
import org.example.player.model.dto.GetClubResponse;
import org.example.player.model.dto.GetClubsResponse;
import org.example.player.model.dto.PutClubRequest;
import org.example.player.service.ClubService;
import java.util.UUID;
@Path("")
public class ClubRestController implements ClubController {
    private final ClubService clubService;
    private final DtoFunctionFactory factory;
    private final UriInfo uriInfo;
    private HttpServletResponse response;
    @Context
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
    @Inject
    public ClubRestController(ClubService clubService, DtoFunctionFactory factory,@SuppressWarnings("CdiInjectionPointsInspection")  UriInfo uriInfo) {
        this.clubService = clubService;
        this.factory = factory;
        this.uriInfo = uriInfo;
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
        request.setId(uuid);
        try {
            clubService.updateClub(factory.requestToClub().apply(request));
            response.setHeader("Location", uriInfo.getBaseUriBuilder()
                    .path(ClubController.class, "getClub")
                    .build(uuid)
                    .toString());
            throw new WebApplicationException(Response.Status.CREATED);
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException(ex);
        }
    }
    @Override
    public void deleteClub(UUID uuid) {
        clubService.deleteClub(Club.builder().id(uuid).build());
    }
}
