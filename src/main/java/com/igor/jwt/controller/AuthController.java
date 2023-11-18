package com.igor.jwt.controller;

import com.igor.jwt.dtos.LoginDTO;
import com.igor.jwt.dtos.RegisterDTO;
import com.igor.jwt.service.AuthenticationService;
import com.igor.jwt.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.token.Token;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {
    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    RegisterService registerService;

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody @Valid LoginDTO loginDTO){
        return ResponseEntity.ok(authenticationService.autenticate(loginDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO){
        registerService.register(registerDTO);
        return ResponseEntity.ok("salvo com sucesso");
    }
}
