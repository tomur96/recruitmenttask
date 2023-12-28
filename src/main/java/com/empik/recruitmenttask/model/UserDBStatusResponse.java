package com.empik.recruitmenttask.model;

public class UserDBStatusResponse {

    String username;
    int apiCallCount;

    public UserDBStatusResponse(String username, int apiCallCount) {
        this.username = username;
        this.apiCallCount = apiCallCount;
    }

    public String getUsername() {
        return username;
    }

    public int getApiCallCount() {
        return apiCallCount;
    }

}