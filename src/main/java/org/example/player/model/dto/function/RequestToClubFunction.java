package org.example.player.model.dto.function;

import org.example.player.entity.Club;
import org.example.player.model.dto.PutClubRequest;

import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;

public class RequestToClubFunction implements BiFunction<UUID,PutClubRequest, Club> {
    @Override
    public Club apply(UUID id, PutClubRequest putClubRequest) {
        return Club.builder()
                .id(id)
                .name(putClubRequest.getName())
                .budget(putClubRequest.getBudget())
                .description(putClubRequest.getDescription())
                .build();
    }
}
