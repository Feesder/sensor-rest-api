package com.project.sensor.conroller;

import com.project.sensor.entity.UserEntity;
import com.project.sensor.exception.UserAlreadyExistException;
import com.project.sensor.exception.UserNotFoundException;
import com.project.sensor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity registerUser(@RequestBody UserEntity user) {
        try {
            userService.registration(user);
            return ResponseEntity.ok().body("Пользователь успешно зарегистрирован");
        } catch(UserAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping
    public ResponseEntity getOneUser(@RequestParam Long id) {
        try {
            return ResponseEntity.ok().body(userService.getOneUser(id));
        } catch(UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch(Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable(value = "id") Long id) {
        try {
            return ResponseEntity.ok().body(userService.deleteUser(id));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
