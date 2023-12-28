package com.empik.recruitmenttask.controller;

import com.empik.recruitmenttask.model.UserDBStatusResponse;
import com.empik.recruitmenttask.model.UserResponse;
import com.empik.recruitmenttask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserResponse> getUserInfo(@PathVariable("username") String username) {
        UserResponse userResponse = userService.getUserInfo(username);

        return ResponseEntity.ok(userResponse);
    }

//    helper endpoint for fetching DB data
    @GetMapping("/dbstatus/{username}")
    public ResponseEntity<UserDBStatusResponse> getDBStatus(@PathVariable("username") String username) {
        UserDBStatusResponse userResponse = userService.getDBStatus(username);

        return ResponseEntity.ok(userResponse);
    }
}
