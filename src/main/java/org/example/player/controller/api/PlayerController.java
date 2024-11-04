package org.example.player.controller.api;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.player.model.dto.GetPlayerResponse;
import org.example.player.model.dto.GetPlayersResponse;
import org.example.player.model.dto.PutPlayerRequest;

import java.util.UUID;
@Path("")
public interface PlayerController {
    @GET
    @Path("/players")
    @Produces(MediaType.APPLICATION_JSON)
    GetPlayersResponse getPlayers();
    @GET
    @Path("/clubs/{id}/players")
    @Produces(MediaType.APPLICATION_JSON)
    GetPlayersResponse getPlayerOfClub(@PathParam("id") UUID id);
    @GET
    @Path("/players/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    GetPlayerResponse getPlayer(@PathParam("id") UUID id);
    @DELETE
    @Path("/players/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    void deletePlayer(@PathParam("id") UUID id);
    @PUT
    @Path("/clubs/{clubId}/players/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void putPlayer(@PathParam("clubId") UUID clubId, @PathParam("id") UUID id, PutPlayerRequest request);
}
