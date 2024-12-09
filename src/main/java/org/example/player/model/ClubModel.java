package org.example.player.model;

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
public class ClubModel {
    private UUID id;
    private String name;
    private String description;
    private Double budget;
    @Singular
    private List<Player> players;

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
        private String overall;
        private Role role;
    }
}
