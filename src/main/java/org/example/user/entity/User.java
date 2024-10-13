package org.example.user.entity;

import lombok.*;
import org.example.player.entity.Player;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class User implements Serializable {
    private UUID id;
    private String username;
    private LocalDate registrationDate;
    private String email;
    @Singular
    private List<Player> players;
}


