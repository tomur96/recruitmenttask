package com.empik.recruitmenttask.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("apicalls")
public class UserEntity {
        @Id
        String username;
        int callCount;

        public String getUsername() {
                return username;
        }

        public int getCallCount() {
                return callCount;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public void setCallCount(int callCount) {
                this.callCount = callCount;
        }

        public UserEntity(String username) {
                this.username = username;
        }
}
