package org.example.player.model;

import lombok.*;
import lombok.EqualsAndHashCode;
import org.example.player.entity.Role;
import org.example.user.model.UserModel;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PlayerEditModel {
    private String name;
    private String overall;
    private Role role;
    private UserModel user;
    private Long version;
}
