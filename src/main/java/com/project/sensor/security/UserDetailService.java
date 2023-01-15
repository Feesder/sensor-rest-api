package com.project.sensor.security;

import com.project.sensor.entity.UserEntity;
import com.project.sensor.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailService  implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUser(username);

        if(userEntity == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }

        return UserDetail.toModel(userEntity);
    }
}
