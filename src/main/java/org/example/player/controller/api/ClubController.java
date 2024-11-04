package org.example.player.controller.api;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.player.model.dto.*;
import java.util.UUID;
public interface ClubController {
    @GET
    @Path("/clubs/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    GetClubResponse getClub(@PathParam("id") UUID uuid);
    @GET
    @Path("/clubs")
    @Produces(MediaType.APPLICATION_JSON)
    GetClubsResponse getClubs();
    @PUT
    @Path("/clubs/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    void putClubs(@PathParam("id") UUID uuid, PutClubRequest request);
    @DELETE
    @Path("/clubs/{id}")
    void deleteClub(@PathParam("id") UUID uuid);
}
