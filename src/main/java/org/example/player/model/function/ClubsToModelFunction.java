package org.example.player.model.function;

import org.example.player.entity.Club;
import org.example.player.model.ClubsModel;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

public class ClubsToModelFunction implements Function<List<Club>, ClubsModel>, Serializable {
    @Override
    public ClubsModel apply(List<Club> entities) {
        return ClubsModel.builder()
                .clubs(entities.stream()
                        .map(club -> ClubsModel.Club.builder()
                                .id(club.getId())
                                .name(club.getName())
                                .description(club.getDescription())
                                .budget(club.getBudget())
                                .build())
                        .toList())
                .build();
    }

}
