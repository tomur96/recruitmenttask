package com.empik.recruitmenttask.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class GithubUserNotFoundException extends RuntimeException {
	public GithubUserNotFoundException(String username) {
		super("User not found on GitHub: " + username);
	}
}
