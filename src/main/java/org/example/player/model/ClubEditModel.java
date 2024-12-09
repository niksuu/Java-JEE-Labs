package org.example.player.model;

import lombok.*;
import org.example.user.model.UserModel;
import org.example.user.model.UsersModel;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class ClubEditModel {
    private UUID id;
    private String name;
    private String description;
    private Double budget;
    private UserModel user;
}
