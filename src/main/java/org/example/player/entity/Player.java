package org.example.player.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.user.entity.User;

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
@ToString
public class Player implements Serializable {
    @Id
    private UUID id;
    private String name;
    private String overall;
    private Role role;
    @ManyToOne
    @JoinColumn(name = "club")
    private Club club;
    @ManyToOne
    @JoinColumn(name = "user_username")
    private User user;


}
