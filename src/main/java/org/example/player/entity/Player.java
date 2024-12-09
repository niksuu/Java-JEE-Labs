package org.example.player.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.club.Club;
import org.example.entity.VersionAndCreationDateAuditable;
import org.example.user.entity.User;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "players")
public class Player extends VersionAndCreationDateAuditable implements Serializable {
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
