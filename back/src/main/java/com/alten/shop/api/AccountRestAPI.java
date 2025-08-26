package com.alten.shop.api;

import com.alten.shop.services.user.AccountService;
import com.alten.shop.utils.dtos.user.input.RegisterRequestDTO;
import com.alten.shop.utils.dtos.user.output.LoginResponseDTO;
import com.alten.shop.utils.dtos.user.output.ProfileResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountRestAPI {

    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    public Map<String, LoginResponseDTO> login(){
        return  Map.of("access_token", new LoginResponseDTO("token"));
    }

    @PostMapping("/register")
    public ResponseEntity<ProfileResponseDTO> signUp(@Valid @RequestBody RegisterRequestDTO user) {
        System.out.println("Controller");
        ProfileResponseDTO profile = accountService.addNewUSer(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(profile);
    }

    @GetMapping("/test")
    public String hello(){
        return "Hello World";
    }
}
