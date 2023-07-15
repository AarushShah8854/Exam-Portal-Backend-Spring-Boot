package com.exam;

import com.exam.helper.UserFoundException;
import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ExamserverApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ExamserverApplication.class, args);
		System.out.println("Application is running");
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Inside run method of CommandLineRunner");

		try {
			User user = new User();
			user.setUsername("aarush8854");
			user.setPassword(this.bCryptPasswordEncoder.encode("abc"));
			user.setFirstName("Aarush");
			user.setLastName("Shah");
			user.setEmail("aarush@gmail.com");
			user.setPhone("879878787");
			user.setProfile("photo.png");

			Role role = new Role();
			role.setRoleId(44L);
			role.setRoleName("ADMIN");

			UserRole userRole = new UserRole();
			userRole.setRole(role);
			userRole.setUser(user);

			Set<UserRole> userRoles = new HashSet<>();
			userRoles.add(userRole);

			User savedUser = this.userService.createUser(user, userRoles);
			System.out.println(savedUser.getUsername());

		} catch (UserFoundException e) {
			e.printStackTrace();
		}

	}
}
