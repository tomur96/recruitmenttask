package com.empik.recruitmenttask.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public record GithubUser(
        String id,
        String login,
        String name,
        String type,
        String avatarUrl,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
        Date createdAt,
        int followers,
        int publicRepos
) {
}
