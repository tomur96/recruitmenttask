package com.empik.recruitmenttask.controller;

import com.empik.recruitmenttask.model.UserResponse;
import com.empik.recruitmenttask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{login}")
    public ResponseEntity<UserResponse> getUserInfo(@PathVariable String login) {
        UserResponse userResponse = userService.getUserInfo(login);
        return ResponseEntity.ok(userResponse);
    }
}
