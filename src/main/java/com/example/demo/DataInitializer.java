package com.example.demo;

import com.example.demo.entities.Employe;
import com.example.demo.repositories.EmployeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner init(EmployeRepository repo, PasswordEncoder encoder) {
        return args -> {

            if (repo.findByEmail("admin@gmail.com").isEmpty()) {

                Employe admin = new Employe();
                admin.setNom("Admin");
                admin.setEmail("admin@gmail.com");
                admin.setPassword(encoder.encode("admin.123"));
                admin.setRole("ADMIN");

                repo.save(admin);
            }
        };
    }
}