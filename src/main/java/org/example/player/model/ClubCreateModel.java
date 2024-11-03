package org.example.player.model;
import lombok.*;
import org.example.player.entity.Player;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class ClubCreateModel {
    private UUID id;
    private String name;
    private String description;
    private Double budget;
}
