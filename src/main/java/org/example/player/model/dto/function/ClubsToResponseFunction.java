package org.example.player.model.dto.function;

import org.example.player.entity.Club;
import org.example.player.model.dto.GetClubsResponse;

import java.util.List;
import java.util.function.Function;

public class ClubsToResponseFunction implements Function<List<Club>, GetClubsResponse> {
    @Override
    public GetClubsResponse apply(List<Club> entities) {
        return GetClubsResponse.builder()
                .clubs(entities.stream()
                        .map(club -> GetClubsResponse.Club.builder()
                                .id(club.getId())
                                .name(club.getName())
                                .description(club.getDescription())
                                .budget(club.getBudget())
                                .build())
                        .toList())
                .build();
    }
}