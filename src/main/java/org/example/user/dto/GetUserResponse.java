package org.example.user.dto;

import lombok.*;
import org.example.player.entity.Role;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetUserResponse {
    private UUID id;
    private String username;
    private String login;
    private LocalDate registrationDate;
    private String email;
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
