package org.example.player.controller.rest;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJB;
import jakarta.ejb.EJBException;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.example.factories.DtoFunctionFactory;
import org.example.player.controller.api.ClubController;
import org.example.player.model.dto.GetClubResponse;
import org.example.player.model.dto.GetClubsResponse;
import org.example.player.model.dto.PatchClubRequest;
import org.example.player.model.dto.PutClubRequest;
import org.example.player.service.ClubService;
import org.example.user.entity.UserRoles;

import java.util.UUID;

@Path("")
@Log
public class ClubRestController implements ClubController {
    private final DtoFunctionFactory factory;
    private ClubService clubService;

    @Inject
    public ClubRestController(DtoFunctionFactory factory) {
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
            throw new BadRequestException(ex);
        }
    }

    @RolesAllowed(UserRoles.ADMIN)
    @Override
    public void deleteClub(UUID uuid) {
        clubService.findClubById(uuid).ifPresentOrElse(
                entity -> clubService.deleteClub(uuid),
                () -> {
                    throw new NotFoundException();
                }
        );
    }


    @Override
    public void patchClub(UUID id, PatchClubRequest request) {
        clubService.findClubById(id).ifPresentOrElse(
                entity -> clubService.updateClub(factory.updateProperty().apply(entity, request)),
                () -> {
                    throw new NotFoundException();
                }
        );
    }
}
