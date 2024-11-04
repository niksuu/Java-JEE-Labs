package org.example.factories;

import jakarta.enterprise.context.ApplicationScoped;
import org.example.player.model.dto.function.*;
import org.example.user.dto.function.RequestToUserFunction;
import org.example.user.dto.function.UserToResponseFunction;
import org.example.user.dto.function.UsersToResponseFunction;

@ApplicationScoped
public class DtoFunctionFactory {
    public UsersToResponseFunction usersToResponse(){return new UsersToResponseFunction();}
    public UserToResponseFunction userToResponse(){return new UserToResponseFunction();}
    public RequestToUserFunction requestToUser(){return new RequestToUserFunction();}
    public ClubToResponseFunction clubToResponse() {return new ClubToResponseFunction();}
    public ClubsToResponseFunction clubsToResponse() { return  new ClubsToResponseFunction();}
    public RequestToClubFunction requestToClub() { return new RequestToClubFunction();}
    public PlayerToResponseFunction playerToResponse() {return new PlayerToResponseFunction();}
    public PlayersToResponseFunction playersToResponse() { return  new PlayersToResponseFunction();}
    public RequestToPlayerFunction requestToPlayer() { return new RequestToPlayerFunction();}
}
