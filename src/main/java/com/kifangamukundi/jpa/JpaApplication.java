package com.kifangamukundi.jpa;

import com.kifangamukundi.jpa.student.entity.Student;
import com.kifangamukundi.jpa.student.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
		// Other stuff like commandline runners
	}

}
