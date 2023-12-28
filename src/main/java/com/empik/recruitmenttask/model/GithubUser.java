package com.empik.recruitmenttask.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class GithubUser {
        private final String id;
        private final String login;
        private final String name;
        private final String type;
        private final String avatarUrl;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
        private final Date createdAt;

        private final int followers;
        private final int publicRepos;

        public GithubUser(String id, String login, String name, String type, String avatarUrl,
                          Date createdAt, int followers, int publicRepos) {
                this.id = id;
                this.login = login;
                this.name = name;
                this.type = type;
                this.avatarUrl = avatarUrl;
                this.createdAt = createdAt;
                this.followers = followers;
                this.publicRepos = publicRepos;
        }

        public String getId() {
                return id;
        }

        public String getLogin() {
                return login;
        }

        public String getName() {
                return name;
        }

        public String getType() {
                return type;
        }

        public String getAvatarUrl() {
                return avatarUrl;
        }

        public Date getCreatedAt() {
                return createdAt;
        }

        public int getFollowers() {
                return followers;
        }

        public int getPublicRepos() {
                return publicRepos;
        }
}