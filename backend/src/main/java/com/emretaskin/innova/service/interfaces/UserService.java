package com.emretaskin.innova.service.interfaces;

import com.emretaskin.innova.entity.User;

public interface UserService {
    void saveUser(User user);

    User loadUserByUsername(String username);

    User findUserById(Long userId);
}
