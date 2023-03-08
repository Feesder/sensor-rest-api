package com.project.sensor.security;

import com.project.sensor.entity.RoleEntity;
import com.project.sensor.exception.AuthException;
import com.project.sensor.repository.UserRepository;
import com.project.sensor.service.UserService;
import io.jsonwebtoken.*;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {

    public enum EToken {
        ACCESS_TOKEN, REFRESH_TOKEN
    }

    @Value("${jwt.token.secret}")
    private String secret;

    @Getter
    @Value("${jwt.token.auth}")
    private String accessCookieName;

    @Getter
    @Value("${jwt.token.refresh}")
    private String refreshCookieName;

    @Getter
    @Value("${jwt.token.path}")
    private String cookiePath;

    @Getter
    @Value("${jwt.token.expired-auth}")
    private Integer accessExpirationCookie;

    @Getter
    @Value("${jwt.token.expired-refresh}")
    private Integer refreshExpirationCookie;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private UserRepository userRepository;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createAccessToken(String user, HttpServletResponse http) {
        Claims claims = Jwts.claims().setSubject(user);
        claims.put("roles", getRoleNames(userRepository.findByUser(user).getRoles()));
        Date now = new Date();
        Date validity = new Date(now.getTime() + accessExpirationCookie);
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
        setToken(token, http, EToken.ACCESS_TOKEN);

        return token;
    }

    public String createRefreshToken(String user, HttpServletResponse http) {
        Claims claims = Jwts.claims().setSubject(user);
        claims.put("roles", getRoleNames(userRepository.findByUser(user).getRoles()));
        Date now = new Date();
        Date validity = new Date(now.getTime() + refreshExpirationCookie);
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
        setToken(token, http, EToken.REFRESH_TOKEN);

        return token;
    }

    public void setToken(String token, HttpServletResponse http, EToken eToken) {
        Cookie cookie = getToken(token, eToken);
        cookie.setPath(getCookiePath());
        cookie.setHttpOnly(true);
        http.addCookie(cookie);
    }

    private Cookie getToken(String token, EToken eToken) {
        Cookie cookie;
        if(eToken.equals(EToken.REFRESH_TOKEN)) {
            cookie = new Cookie(getRefreshCookieName(), token);
            cookie.setMaxAge(getRefreshExpirationCookie());
        } else {
            cookie = new Cookie(getAccessCookieName(), token);
            cookie.setMaxAge(getAccessExpirationCookie());
        }
        return cookie;
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailService.loadUserByUsername(getUser(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUser(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) throws AuthException {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch(JwtException | IllegalArgumentException e) {
            throw new AuthException("Токен JWT просрочен или недействителен");
        }
    }

    private List<String> getRoleNames(List<RoleEntity> userRoles) {
        List<String> roles = new ArrayList<>();
        userRoles.forEach(role -> {
            roles.add(role.getName());
        });
        return roles;
    }
}
