package org.example.player.entity;


import org.example.user.entity.User;
import lombok.*;
import java.io.Serializable;
import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class Player implements Serializable{
    private UUID id;
    private String name;
    private Integer overall;
    private Role role;
    private Club club;
    private User user;


    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", overall='" + overall + '\'' +
                ", Role=" + role +
                ", club=" + club.getName() +
                ", user=" + user.getUsername() +
                '}';
    }
}
