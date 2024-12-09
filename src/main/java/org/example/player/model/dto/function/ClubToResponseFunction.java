package org.example.player.model.dto.function;

import org.example.club.Club;
import org.example.player.model.dto.GetClubResponse;

import java.util.function.Function;

public class ClubToResponseFunction implements Function<Club, GetClubResponse> {
    @Override
    public GetClubResponse apply(Club club) {
        return GetClubResponse.builder()
                .id(club.getId())
                .name(club.getName())
                .description(club.getDescription())
                .budget(club.getBudget())
                .players(club.getPlayers().stream()
                        .map(player -> GetClubResponse.Player.builder()
                                .name(player.getName())
                                .role(player.getRole())
                                .overall(player.getOverall())
                                .id(player.getId())
                                .build())
                        .toList())
                .build();
    }
}
