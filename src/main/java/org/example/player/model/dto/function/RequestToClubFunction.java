package org.example.player.model.dto.function;

import org.example.player.entity.Club;
import org.example.player.model.dto.PutClubRequest;

import java.util.function.Function;

public class RequestToClubFunction implements Function<PutClubRequest, Club>{
    @Override
    public Club apply(PutClubRequest putClubRequest) {
        return Club.builder()
                .id(putClubRequest.getId())
                .name(putClubRequest.getName())
                .budget(putClubRequest.getBudget())
                .description(putClubRequest.getDescription())
                .build();
    }
}
