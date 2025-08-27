package com.alten.shop.api;

import com.alten.shop.services.user.AccountService;
import com.alten.shop.utils.dtos.user.input.LoginRequestDTO;
import com.alten.shop.utils.dtos.user.input.RegisterRequestDTO;
import com.alten.shop.utils.dtos.user.output.LoginResponseDTO;
import com.alten.shop.utils.dtos.user.output.ProfileResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountRestAPI {

    @Autowired
    private AccountService accountService;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password())
        );
        LoginResponseDTO loginResponse = accountService.login(loginRequest, authentication)
                .orElseThrow(() -> new RuntimeException("Failed to login ..."));
        return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<ProfileResponseDTO> signUp(@Valid @RequestBody RegisterRequestDTO user) {
        ProfileResponseDTO profile = accountService.addNewUSer(user)
                .orElseThrow(() -> new RuntimeException("Failed to create user"));
        return ResponseEntity.status(HttpStatus.CREATED).body(profile);
    }


    @GetMapping("/users")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<List<ProfileResponseDTO>> getAllUsers() {
        List<ProfileResponseDTO> users = accountService.loadAllUsers()
                .orElseThrow(() -> new RuntimeException("Failed to load users"));
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }



}
