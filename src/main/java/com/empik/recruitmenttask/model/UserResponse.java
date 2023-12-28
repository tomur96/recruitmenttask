package com.empik.recruitmenttask.model;

import java.util.Date;

public class UserResponse {
    private final String id;
    private final String login;
    private final String name;
    private final String type;
    private final String avatarUrl;
    private final Date createdAt;
    private final double calculations;

    public UserResponse(String id, String login, String name, String type, String avatarUrl,
                        Date createdAt, double calculations) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.type = type;
        this.avatarUrl = avatarUrl;
        this.createdAt = createdAt;
        this.calculations = calculations;
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

    public double getCalculations() {
        return calculations;
    }
}