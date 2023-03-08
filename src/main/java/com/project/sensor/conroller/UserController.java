package com.project.sensor.conroller;

import com.project.sensor.entity.UserEntity;
import com.project.sensor.exception.TokenNotExist;
import com.project.sensor.exception.UserNotFoundException;
import com.project.sensor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity getOneUser(@RequestParam Long id) {
        try {
            return ResponseEntity.ok().body(userService.findById(id));
        } catch(UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/refresh")
    public ResponseEntity getUserByRefresh(@CookieValue(name = "REFRESH-TOKEN") Cookie cookie, HttpServletResponse response) {
        try {
            return ResponseEntity.ok().body(userService.findByToken(cookie.getValue(), response));
        } catch(TokenNotExist e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/refresh/{value}")
    public ResponseEntity getUserByRefresh(@PathVariable String value, HttpServletResponse response) {
        try {
            return ResponseEntity.ok().body(userService.findByToken(value, response));
        } catch(UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PutMapping
    public ResponseEntity updateUser(@RequestParam Long id, @RequestBody UserEntity userEntity) {
        try {
            return ResponseEntity.ok().body(userService.updateUser(id, userEntity));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body("Произошла Ошибка");
        }
    }

    @DeleteMapping
    public ResponseEntity deleteUser(@RequestParam Long id) {
        try {
            return ResponseEntity.ok().body(userService.deleteUser(id));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
