package org.example.player.model.dto;

import lombok.*;
import org.example.player.entity.Role;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PatchPlayerRequest {
    private String name;
    private String overall;
    private Role role;
}
