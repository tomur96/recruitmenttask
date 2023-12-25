package com.empik.recruitmenttask.model;

import java.util.Date;

public record UserResponse(
        String id,
        String login,
        String name,
        String type,
        String avatarUrl,
        Date createdAt,
        double calculations
) {
}