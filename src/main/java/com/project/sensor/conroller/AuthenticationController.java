package com.project.sensor.conroller;

import com.project.sensor.entity.UserEntity;
import com.project.sensor.exception.UserAlreadyExistException;
import com.project.sensor.exception.UserNotFoundException;
import com.project.sensor.exception.WrongPasswordException;
import com.project.sensor.model.authorization.LoginRequest;
import com.project.sensor.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @PostMapping("/registration")
    public ResponseEntity registration(@RequestBody UserEntity user, HttpServletResponse http) {
        try {
            return ResponseEntity.ok().body(userService.registration(user, http));
        } catch(UserAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody LoginRequest user, HttpServletResponse http) {
        try {
            return ResponseEntity.ok().body(userService.login(user, http));
        } catch(UserNotFoundException | WrongPasswordException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity logout(HttpServletResponse http) {
        try {
            userService.clearAuthAndRefreshTokens(http);
            return ResponseEntity.ok().body("Успешно");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
