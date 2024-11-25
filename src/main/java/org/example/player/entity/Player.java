package org.example.player.entity;


import jakarta.persistence.*;
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
@Entity
@Table(name = "players")
public class Player implements Serializable{
    @Id
    private UUID id;
    private String name;
    private Integer overall;
    private Role role;
    @ManyToOne
    @JoinColumn(name="club")
    private Club club;
    @ManyToOne
    @JoinColumn(name = "user_name")
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
