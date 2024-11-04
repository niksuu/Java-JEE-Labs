package org.example.player.model.dto;

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
public class PutPlayerRequest {
    private UUID id;
    private String name;
    private Integer overall;
    private Role role;
    private UUID club;
    private UUID user;
}
