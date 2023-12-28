package com.empik.recruitmenttask.service;

import com.empik.recruitmenttask.model.UserDBStatusResponse;
import com.empik.recruitmenttask.model.UserResponse;

public interface UserService {

    UserResponse getUserInfo(String username);
    UserDBStatusResponse getDBStatus(String username);
}
