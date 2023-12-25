package com.empik.recruitmenttask.service;

import com.empik.recruitmenttask.model.GithubUser;
import com.empik.recruitmenttask.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public record UserService(RestTemplate restTemplate) {

    @Autowired
    public UserService {
    }

    public UserResponse getUserInfo(String login) {
        GithubUser githubUser = restTemplate.getForObject("https://api.github.com/users/" + login, GithubUser.class);

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

    private double calculations(int followers, int publicRepos) {
        return  6.0 / (followers * (2 + publicRepos));
    }
}