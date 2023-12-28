package com.empik.recruitmenttask.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("apicalls")
public class UserEntity {
        @Id
        String login;
        int request_count;

        public String getLogin() {
                return login;
        }

        public int getRequest_count() {
                return request_count;
        }

        public void setRequest_count(int request_count) {
                this.request_count = request_count;
        }

        public UserEntity(String login) {
                this.login = login;
        }
}
