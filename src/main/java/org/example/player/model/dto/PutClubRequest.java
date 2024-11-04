package org.example.player.model.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutClubRequest {
    private UUID id;
    private String name;
    private String description;
    private Double budget;
}