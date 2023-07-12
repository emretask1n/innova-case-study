package com.emretaskin.innova.service.interfaces;

import com.emretaskin.innova.dto.request.UserRequest;
import com.emretaskin.innova.dto.response.UserResponse;
import com.emretaskin.innova.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);

    User loadUserByUsername(String username);

    User findUserById(Long userId);

    void deleteUser(Long id);

    UserResponse updateUser(Long id, UserRequest userRequest);

    List<User> getAllUsers();

}
