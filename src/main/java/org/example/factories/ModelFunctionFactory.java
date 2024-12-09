package org.example.factories;

import jakarta.enterprise.context.ApplicationScoped;
import org.example.player.model.PlayersModel;
import org.example.player.model.function.*;
import org.example.user.model.function.UserToModelFunction;
import org.example.user.model.function.UsersToModelFunction;

@ApplicationScoped
public class ModelFunctionFactory {
    public UsersToModelFunction usersToModel() {
        return new UsersToModelFunction();
    }

    public ClubsToModelFunction clubsToModel() {
        return new ClubsToModelFunction();
    }

    public ClubToModelFunction clubToModel() {
        return new ClubToModelFunction();
    }

    public ClubToEditModelFunction clubToEditModel() {
        return new ClubToEditModelFunction();
    }

    public UpdateClubWithModelFunction updateClub() {
        return new UpdateClubWithModelFunction();
    }

    public PlayerToModelFunction playerToModel() {
        return new PlayerToModelFunction();
    }

    public ModelToClubFunction modelToClub() {
        return new ModelToClubFunction();
    }

    public UserToModelFunction userToModel() {
        return new UserToModelFunction();
    }
    public PlayersToModelFunction playersToModel() {
        return new PlayersToModelFunction();
    }
}
