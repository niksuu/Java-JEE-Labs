package org.example.factories;


import jakarta.enterprise.context.ApplicationScoped;
import org.example.player.model.function.*;

@ApplicationScoped
public class ModelFunctionFactory {

    public ClubToModelFunction clubToModelFunction() {
        return new ClubToModelFunction();
    }

    public ClubsToModelFunction clubsToModelFunction() {
        return new ClubsToModelFunction();
    }

    public ClubToEditModelFunction clubToEditModelFunction() {
        return new ClubToEditModelFunction();
    }

    public UpdateClubWithModelFunction updateClubWithModelFunction() {
        return new UpdateClubWithModelFunction();
    }

    public ModelToClubFunction modelToClubFunction() {
        return new ModelToClubFunction();
    }

    public PlayerToModelFunction playerToModelFunction() {
        return new PlayerToModelFunction();
    }

}
