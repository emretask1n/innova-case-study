package com.emretaskin.innova.controller;

import com.emretaskin.innova.dto.request.UserRequest;
import com.emretaskin.innova.dto.response.UserResponse;
import com.emretaskin.innova.service.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/{userId}")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "Update a user by user ID")
    @PutMapping
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long userId, @Valid @RequestBody UserRequest userRequest) {
        UserResponse updatedUser = userService.updateUser(userId, userRequest);
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(summary = "Delete a user by user ID")
    @DeleteMapping
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }
}
