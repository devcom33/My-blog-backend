package org.heymouad.blog.controllers;

import lombok.RequiredArgsConstructor;
import org.heymouad.blog.domain.dtos.AuthResponse;
import org.heymouad.blog.domain.dtos.LoginRequest;
import org.heymouad.blog.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        UserDetails userDetails = authenticationService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        String tokenContent = authenticationService.generateToken(userDetails);

        AuthResponse authResponse = AuthResponse.builder().token(tokenContent).expiresIn(172800).build();

        return ResponseEntity.ok(authResponse);
    }
}
