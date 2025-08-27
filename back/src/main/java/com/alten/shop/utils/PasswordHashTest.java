package com.alten.shop.utils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordHashTest implements CommandLineRunner {
    
    @Override
    public void run(String... args) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = "figuig";
        String hashedPassword = encoder.encode(password);
        
        System.out.println("=== HASH GENERATOR ===");
        System.out.println("Mot de passe: " + password);
        System.out.println("Hash BCrypt: " + hashedPassword);
        System.out.println("=====================");
    }
}