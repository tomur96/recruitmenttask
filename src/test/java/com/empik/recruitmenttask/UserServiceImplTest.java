package com.empik.recruitmenttask;

import com.empik.recruitmenttask.exception.AppException;
import com.empik.recruitmenttask.exception.UserNotFoundException;
import com.empik.recruitmenttask.model.GithubUser;
import com.empik.recruitmenttask.model.UserResponse;
import com.empik.recruitmenttask.repository.UserEntity;
import com.empik.recruitmenttask.repository.UserRepository;
import com.empik.recruitmenttask.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static com.empik.recruitmenttask.TestUtils.*;

@SpringBootTest
class UserServiceImplTest {

    private final RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private final UserServiceImpl userService = new UserServiceImpl(restTemplate, userRepository);

    @Test
    void getUserInfo_UserFound_ReturnsUserResponse() {
        // Arrange  GIVEN
        String username = "testuser";
        String apiUrl = "https://api.github.com/users/" + username;

        GithubUser githubUser = sampleGithubUser();
        when(restTemplate.getForEntity(apiUrl, GithubUser.class))
                .thenReturn(new ResponseEntity<>(githubUser, HttpStatus.OK));

        UserEntity userEntity = sampleUserEntity();
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
        when(userRepository.findById(username)).thenReturn(java.util.Optional.of(userEntity));

        // Act  WHEN
        UserResponse result = userService.getUserInfo(username);

        // Assert   THEN
        assertNotNull(result);
        // Add more assertions based on your specific test data
    }

    @Test
    void getUserInfo_UserNotFound_ThrowsUserNotFoundException() {
        // GIVEN
        String username = "nonexistentuser";
        String apiUrl = "https://api.github.com/users/" + username;

        // WHEN
        when(restTemplate.getForEntity(apiUrl, GithubUser.class))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        // THEN
        assertThrows(UserNotFoundException.class, () -> userService.getUserInfo(username));
    }

    @Test
    void getUserInfo_GeneralError_ThrowsUserServiceException() {
        // GIVEN
        String username = "testuser";
        String apiUrl = "https://api.github.com/users/" + username;

        // WHEN
        when(restTemplate.getForEntity(apiUrl, GithubUser.class))
                .thenThrow(new RuntimeException("Some unexpected error"));

        // THEN
        assertThrows(AppException.class, () -> userService.getUserInfo(username));
    }
}
