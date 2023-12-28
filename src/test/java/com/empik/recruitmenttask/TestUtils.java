package com.empik.recruitmenttask;

import com.empik.recruitmenttask.model.GithubUser;
import com.empik.recruitmenttask.model.UserEntity;

import java.util.Date;

public class TestUtils {

    public static GithubUser sampleGithubUser() {
        return new GithubUser(
                "123",
                "testuser",
                "Test User",
                "user",
                "https://avatar.url",
                new Date(),
                100,
                20
        );
    }

    public static UserEntity sampleUserEntity() {
        UserEntity userEntity = new UserEntity("testuser");
        userEntity.setRequest_count(0);
        return userEntity;
    }
}