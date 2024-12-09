package org.example.club;

import jakarta.persistence.*;
import lombok.*;
import org.example.player.entity.Player;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "clubs")
public class Club implements Serializable {
    @Id
    private UUID id;
    private String name;
    private String description;
    private Double budget;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "club", cascade = CascadeType.REMOVE)
    private List<Player> players;


}
