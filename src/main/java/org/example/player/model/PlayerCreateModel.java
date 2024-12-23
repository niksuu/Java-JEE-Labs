package org.example.player.model;

import jakarta.servlet.http.Part;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.example.player.entity.Role;
import org.example.player.model.ClubModel;
import org.example.player.model.ClubsModel;

import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PlayerCreateModel {


    private UUID id;
    private String name;
    private Integer overall;
    private Role role;


    private ClubModel club;

}