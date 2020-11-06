package com.example.loginrestservice;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/login")
	public Login login(@RequestParam(value = "username") String username,@RequestParam(value = "password") String password) {
		return new Login(counter.incrementAndGet(), String.format(template, username), password);
	}
}
