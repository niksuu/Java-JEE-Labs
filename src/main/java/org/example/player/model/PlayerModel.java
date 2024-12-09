package org.example.player.model;

import lombok.*;
import org.example.player.entity.Role;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PlayerModel {
    private UUID id;
    private String name;
    private String overall;
    private Role role;
    private String club;
    private String user;
}
