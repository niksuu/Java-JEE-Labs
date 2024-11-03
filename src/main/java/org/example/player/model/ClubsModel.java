package org.example.player.model;
import lombok.*;

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
public class ClubsModel implements Serializable {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Club implements Serializable {
        private UUID id;
        private String name;
        private String description;
        private Double budget;
    }

    @Singular
    private List<Club> clubs;

}
