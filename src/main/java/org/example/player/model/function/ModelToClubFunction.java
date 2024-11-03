package org.example.player.model.function;
import org.example.player.entity.Club;
import org.example.player.model.ClubCreateModel;
import java.io.Serializable;
import java.util.function.Function;
public class ModelToClubFunction implements Function<ClubCreateModel, Club>, Serializable {
    @Override
    public Club apply(ClubCreateModel entity) {
        return Club.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .budget(entity.getBudget())
                .build();
    }
}
