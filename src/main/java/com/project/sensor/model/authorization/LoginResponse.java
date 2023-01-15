package com.project.sensor.model.authorization;

import com.project.sensor.entity.UserEntity;
import com.project.sensor.model.User;

import java.util.List;

public class LoginResponse extends JwtTokenResponse {

    public LoginResponse(User user, String accessToken, String refreshToken) {
        super(user, accessToken, refreshToken);
    }

    public static LoginResponse toModel(UserEntity userEntity, String accessToken, String refreshToken) {
        return new LoginResponse(
                User.toModel(userEntity),
                accessToken,
                refreshToken
        );
    }
}
