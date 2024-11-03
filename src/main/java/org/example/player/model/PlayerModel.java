package org.example.player.model;
import lombok.ToString;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.example.player.entity.Club;
import org.example.player.entity.Role;
import org.example.user.entity.User;
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
    private Integer overall;
    private Role role;
    private String club;
    private String user;
}