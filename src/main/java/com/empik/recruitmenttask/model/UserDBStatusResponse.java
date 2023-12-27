package com.empik.recruitmenttask.model;

public record UserDBStatusResponse(
        String username,
        int callCount
) {
}