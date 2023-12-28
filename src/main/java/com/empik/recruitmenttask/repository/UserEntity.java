package com.empik.recruitmenttask.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("apicalls")
public class UserEntity {
        @Id
        String username;
        int apiCallCount;

        public String getUsername() {
                return username;
        }

        public int getApiCallCount() {
                return apiCallCount;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public void setApiCallCount(int apiCallCount) {
                this.apiCallCount = apiCallCount;
        }

        public UserEntity(String username) {
                this.username = username;
        }
}
