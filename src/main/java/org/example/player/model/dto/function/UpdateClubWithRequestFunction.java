package org.example.player.model.dto.function;

import org.example.club.Club;
import org.example.player.model.dto.PatchClubRequest;

import java.util.function.BiFunction;

public class UpdateClubWithRequestFunction implements BiFunction<Club, PatchClubRequest, Club> {
    @Override
    public Club apply(Club club, PatchClubRequest patchClubRequest) {
        return Club.builder()
                .id(club.getId())
                .description(patchClubRequest.getDescription())
                .budget(patchClubRequest.getBudget())
                .name(patchClubRequest.getName())
                .build();
    }
}