package com.empik.recruitmenttask.service;

import com.empik.recruitmenttask.model.GithubUser;
import com.empik.recruitmenttask.model.UserDBStatusResponse;
import com.empik.recruitmenttask.model.UserResponse;
import com.empik.recruitmenttask.repository.UserRepository;
import com.empik.recruitmenttask.repository.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final RestTemplate restTemplate;
    private final UserRepository userRepository;

    public UserService(RestTemplate restTemplate, UserRepository userRepository) {
        this.restTemplate = restTemplate;
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse getUserInfo(String username) {

        GithubUser githubUser = restTemplate.getForObject("https://api.github.com/users/" + username, GithubUser.class);


        UserEntity userEntity = findUser(username);
        if (userEntity == null) {
            return null;
        }

        userEntity.setCallCount(incrementCallCount(userEntity.getCallCount()));
        userRepository.save(userEntity);


        double calculations = calculations(Objects.requireNonNull(githubUser).followers(), githubUser.publicRepos());

        return new UserResponse(
                githubUser.id(),
                githubUser.login(),
                githubUser.name(),
                githubUser.type(),
                githubUser.avatarUrl(),
                githubUser.createdAt(),
                calculations
        );
    }

    @Override
    public UserDBStatusResponse getDBStatus(String username) {

        UserEntity userEntity = findUser(username);
        if (userEntity == null) {
            return null;
        }

        return new UserDBStatusResponse(
                userEntity.getUsername(),
                userEntity.getCallCount()
        );
    }

    private double calculations(int followers, int publicRepos) {
        return  6.0 / (followers * (2 + publicRepos));
    }

    private UserEntity findUser(String username) {
        return Optional.of(userRepository.findById(username)).get().orElse(null);
    }

    private int incrementCallCount(int callCount) {
        return callCount+1;
    }
}