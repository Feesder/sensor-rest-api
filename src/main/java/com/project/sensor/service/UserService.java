package com.project.sensor.service;

import com.project.sensor.entity.UserEntity;
import com.project.sensor.exception.UserAlreadyExistException;
import com.project.sensor.exception.UserNotFoundException;
import com.project.sensor.model.User;
import com.project.sensor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity registration(UserEntity userEntity) throws UserAlreadyExistException {
        if(userRepository.findByUser(userEntity.getUser()) != null) {
            throw new UserAlreadyExistException("Пользователь с таком именем уже существует");
        }
        return userRepository.save(userEntity);
    }

    public User getOneUser(Long id) throws UserNotFoundException {
        UserEntity user = userRepository.findById(id).get();
        if(user == null) {
            throw new UserNotFoundException("Пользователь не найден");
        }
        return User.toModel(user);
    }

    public Long deleteUser(Long id) {
        userRepository.deleteById(id);
        return id;
    }
}
