package com.project.sensor.service;

import com.project.sensor.entity.RoleEntity;
import com.project.sensor.entity.UserEntity;
import com.project.sensor.exception.TokenNotExist;
import com.project.sensor.exception.UserAlreadyExistException;
import com.project.sensor.exception.UserNotFoundException;
import com.project.sensor.exception.WrongPasswordException;
import com.project.sensor.model.authorization.JwtTokenResponse;
import com.project.sensor.model.authorization.RegistrationResponse;
import com.project.sensor.model.User;
import com.project.sensor.repository.DeviceRepository;
import com.project.sensor.repository.RoleRepository;
import com.project.sensor.repository.UserRepository;
import com.project.sensor.security.JwtTokenProvider;
import com.project.sensor.model.authorization.LoginResponse;
import com.project.sensor.model.authorization.LoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService {

    public enum EToken {
        ACCESS_TOKEN, REFRESH_TOKEN
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager AuthenticationManager;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public RegistrationResponse registration(UserEntity user, HttpServletResponse response) throws UserAlreadyExistException {
        if(userRepository.existsByUser(user.getUser())) {
            throw new UserAlreadyExistException("Пользователь с таким именем уже существует");
        }
        if(userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistException("Пользователь с почтовым адресом {email} уже существует".replace("{email}", user.getEmail()));
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        List<RoleEntity> roles = new ArrayList<>();
        roles.add(roleRepository.findByName("ROLE_USER"));
        if(user.getRoles() != null) {
            user.getRoles().forEach(value -> roles.add(roleRepository.findByName(value.getName())));
        }
        user.setRoles(roles);
        String accessToken = jwtTokenProvider.createAccessToken(user.getUser());
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getUser());
        setToken(accessToken, response, EToken.ACCESS_TOKEN);
        setToken(refreshToken, response, EToken.REFRESH_TOKEN);
        return RegistrationResponse.toModel(userRepository.save(user),
                accessToken,
                refreshToken);
    }

    public LoginResponse login(LoginRequest loginRequest, HttpServletResponse response) throws UserNotFoundException, WrongPasswordException {
        UserEntity user = userRepository.findByUser(loginRequest.getUser());

        if(user == null) {
            throw new UserNotFoundException("Пользовать с таким логином не найден");
        }

        try {
            AuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getUser(), loginRequest.getPassword()
            ));
        } catch (Exception e) {
            throw new WrongPasswordException("Не верный пароль");
        }

        String accessToken = jwtTokenProvider.createAccessToken(user.getUser());
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getUser());
        setToken(accessToken, response, EToken.ACCESS_TOKEN);
        setToken(refreshToken, response, EToken.REFRESH_TOKEN);
        return LoginResponse.toModel(userRepository.save(user),
                accessToken,
                refreshToken);
    }

    public User findById(Long id) throws UserNotFoundException {
        UserEntity user = userRepository.findById(id).get();


        if(user == null) {
            throw new UserNotFoundException("Пользователь не найден");
        }
        return User.toModel(user);
    }

    public JwtTokenResponse findByToken(String token, HttpServletResponse response) throws TokenNotExist {
        if(token == null) {
            throw new TokenNotExist("Токен не существует");
        }

        String user = jwtTokenProvider.getUser(token);
        UserEntity userEntity = userRepository.findByUser(user);
        String accessToken = jwtTokenProvider.createAccessToken(userEntity.getUser());
        String refreshToken = jwtTokenProvider.createRefreshToken(userEntity.getUser());
        setToken(accessToken, response, EToken.ACCESS_TOKEN);
        setToken(refreshToken, response, EToken.REFRESH_TOKEN);
        return JwtTokenResponse.toModel(userEntity,
                accessToken,
                refreshToken);
    }

    public UserEntity findByUser(String user) throws UserNotFoundException {
        UserEntity userEntity = userRepository.findByUser(user);
        if(user == null) {
            throw new UserNotFoundException("Пользователь не найден");
        }
        return userEntity;
    }

    public User updateUser(Long id, UserEntity userEntity) throws UserNotFoundException {
        UserEntity user = userRepository.findById(id).get();
        if(user == null) {
            throw new UserNotFoundException("Пользователь не найден");
        }
        user.setUser(userEntity.getUser());
        user.setPassword(userEntity.getPassword());
        return User.toModel(user);
    }

    public Long deleteUser(Long id) {
        userRepository.deleteById(id);
        return id;
    }

    public void clearAuthAndRefreshTokens(HttpServletResponse httpServletResponse) {
        Cookie authCookie = new Cookie(jwtTokenProvider.getAccessCookieName(), "-");
        authCookie.setPath(jwtTokenProvider.getCookiePath());

        Cookie refreshCookie = new Cookie(jwtTokenProvider.getRefreshCookieName(), "-");
        refreshCookie.setPath(jwtTokenProvider.getCookiePath());

        httpServletResponse.addCookie(authCookie);
        httpServletResponse.addCookie(refreshCookie);
    }

    public void setToken(String token, HttpServletResponse http, EToken eToken) {
        if(eToken.equals(EToken.REFRESH_TOKEN)) {
            http.addHeader(
                    "Set-Cookie",
                    "REFRESH-TOKEN=" + token + "; HttpOnly; SameSite=Lax; Max-Age=" + jwtTokenProvider.getRefreshExpirationCookie()
                    );
        } else {
            http.addHeader(
                    "Set-Cookie",
                    "ACCESS-TOKEN=" + token + "; HttpOnly; SameSite=Lax; Max-Age=" + jwtTokenProvider.getAccessExpirationCookie()
            );
        }
    }
}
