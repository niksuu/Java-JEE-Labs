package org.example.user.dto;

import lombok.*;
import org.example.player.entity.Role;

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
public class PutUserRequest {
    private UUID id;
    private String username;
    private LocalDate registrationDate;
    private String email;
}
