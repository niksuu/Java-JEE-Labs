package org.example.user.dto.function;

import org.example.user.dto.GetUserResponse;
import org.example.user.entity.User;

import java.util.function.Function;

public class UserToResponseFunction implements Function<User, GetUserResponse> {
    @Override
    public GetUserResponse apply(User user) {
        return GetUserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .login(user.getLogin())
                .registrationDate(user.getRegistrationDate())
                .players(user.getPlayers().stream()
                        .map(player -> GetUserResponse.Player.builder()
                                .name(player.getName())
                                .role(player.getRole())
                                .overall(player.getOverall())
                                .id(player.getId())
                                .build())
                        .toList())
                .build();
    }
}
