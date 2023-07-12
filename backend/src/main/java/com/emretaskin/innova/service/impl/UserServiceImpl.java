package com.emretaskin.innova.service.impl;

import com.emretaskin.innova.entity.User;
import com.emretaskin.innova.exception.UserNotFoundException;
import com.emretaskin.innova.repository.UserRepository;
import com.emretaskin.innova.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User loadUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    @Override
    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id : " + userId));
    }
}
