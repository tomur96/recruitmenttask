package com.empik.recruitmenttask.service;

import com.empik.recruitmenttask.exception.AppException;
import com.empik.recruitmenttask.exception.GithubUserNotFoundException;
import com.empik.recruitmenttask.exception.UserNotFoundException;
import com.empik.recruitmenttask.model.GithubUser;
import com.empik.recruitmenttask.model.UserDBStatusResponse;
import com.empik.recruitmenttask.model.UserResponse;
import com.empik.recruitmenttask.repository.UserEntity;
import com.empik.recruitmenttask.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {


    private final RestTemplate restTemplate;
    private final UserRepository userRepository;

    public UserServiceImpl(RestTemplate restTemplate, UserRepository userRepository) {
        this.restTemplate = restTemplate;
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse getUserInfo(String username) {
        try {
            String githubUrl = "https://api.github.com/users/" + username;
            GithubUser githubUser = restTemplate.getForObject(githubUrl, GithubUser.class);


            if (githubUser != null) {
                UserEntity userEntity = findUser(username);

                userEntity.setApiCallCount(incrementCallCount(userEntity.getApiCallCount()));
                userRepository.save(userEntity);

                double calculations = calculate(githubUser.getFollowers(), githubUser.getPublicRepos());

                return new UserResponse(
                        githubUser.getId(),
                        githubUser.getLogin(),
                        githubUser.getName(),
                        githubUser.getType(),
                        githubUser.getAvatarUrl(),
                        githubUser.getCreatedAt(),
                        calculations
                );
            } else {
                throw new GithubUserNotFoundException(username);
            }
        } catch (Exception e) {
            throw new AppException("Error while fetching user data", e);
        }
    }

    @Override
    public UserDBStatusResponse getDBStatus(String username) {

        UserEntity userEntity = findUser(username);

        return new UserDBStatusResponse(
                userEntity.getUsername(),
                userEntity.getApiCallCount()
        );
    }

    private UserEntity findUser(String username) {
        return userRepository.findById(username).orElseThrow(() -> new UserNotFoundException(username));
    }

    private double calculate(int followers, int publicRepos) {
        //logic provided by business without deeper explanations
        return 6.0 / (followers * (2 + publicRepos));
    }

    private int incrementCallCount(int callCount) {
        return callCount + 1;
    }
}