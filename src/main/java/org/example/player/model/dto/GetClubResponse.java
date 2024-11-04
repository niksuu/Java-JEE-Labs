package org.example.player.model.dto;

import lombok.*;
import org.example.player.entity.Role;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetClubResponse {
    private UUID id;
    private String name;
    private String description;
    private Double budget;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Player {
        private UUID id;
        private String name;
        private Integer overall;
        private Role role;
    }
    @Singular
    private List<Player> players;
}
