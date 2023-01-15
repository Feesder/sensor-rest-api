package com.project.sensor.model.authorization;

import com.project.sensor.entity.UserEntity;
import com.project.sensor.model.User;

import java.util.List;

public class RegistrationResponse extends JwtTokenResponse {
    public RegistrationResponse(User user, String accessToken, String refreshToken) {
        super(user, accessToken, refreshToken);
    }

    public static RegistrationResponse toModel(UserEntity userEntity, String accessToken, String refreshToken) {
        return new RegistrationResponse(
                User.toModel(userEntity),
                accessToken,
                refreshToken
        );
    }
}
