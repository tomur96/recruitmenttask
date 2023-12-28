package com.empik.recruitmenttask;

import com.empik.recruitmenttask.exception.GithubUserNotFoundException;
import com.empik.recruitmenttask.exception.UserNotFoundException;
import com.empik.recruitmenttask.model.GithubUser;
import com.empik.recruitmenttask.model.UserDBStatusResponse;
import com.empik.recruitmenttask.model.UserEntity;
import com.empik.recruitmenttask.model.UserResponse;
import com.empik.recruitmenttask.repository.UserRepository;
import com.empik.recruitmenttask.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static com.empik.recruitmenttask.TestUtils.sampleGithubUser;
import static com.empik.recruitmenttask.TestUtils.sampleUserEntity;
import static java.util.Optional.empty;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private UserRepository userRepository;

    @Test
    void getUserInfo_ok() {
        // Arrange
        String username = "testuser";
        GithubUser githubUser = sampleGithubUser();
        UserEntity userEntity = sampleUserEntity();
        userEntity.setRequest_count(5);

        when(restTemplate.getForObject(anyString(), eq(GithubUser.class))).thenReturn(githubUser);
        when(userRepository.findById(username)).thenReturn(Optional.of(userEntity));

        // Act
        UserResponse result = userService.getUserInfo(username);

        // Assert
        assertNotNull(result);
        assertEquals("123", result.getId());
        assertEquals("testuser", result.getLogin());
        assertEquals("Test User", result.getName());
        assertEquals("user", result.getType());
        assertEquals("https://avatar.url", result.getAvatarUrl());
    }

    @Test
    void getUserInfo_GithubUserNotFoundException() {
        // Arrange
        String username = "nonexistentuser";

        when(restTemplate.getForObject(anyString(), eq(GithubUser.class))).thenReturn(null);

        // Assert
        assertThrows(GithubUserNotFoundException.class, () -> userService.getUserInfo(username));
    }

    @Test
    void getDBStatus_ok() {
        // Arrange
        String username = "testuser";
        UserEntity userEntity = new UserEntity(username);
        userEntity.setRequest_count(5);

        when(userRepository.findById(username)).thenReturn(Optional.of(userEntity));

        // Act
        UserDBStatusResponse result = userService.getDBStatus(username);

        // Assert
        assertEquals(username, result.getUsername());
        assertEquals(5, result.getApiCallCount());
    }

    @Test
    void getDBStatus_UserNotFoundException() {
        // Arrange
        String username = "nonexistentuser";

        when(userRepository.findById(username)).thenReturn(empty());

        // Assert
        assertThrows(UserNotFoundException.class, () -> userService.getDBStatus(username));
    }
}
