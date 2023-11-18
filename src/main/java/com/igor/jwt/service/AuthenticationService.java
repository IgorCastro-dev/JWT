package com.igor.jwt.service;

import com.igor.jwt.dtos.LoginDTO;
import com.igor.jwt.dtos.TokenDto;
import com.igor.jwt.model.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.token.Token;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;
    public Token autenticate(LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),loginDTO.getPassword());
        var auth = authenticationManager.authenticate(authenticationToken);
        UserCredentials userCredentials = (UserCredentials) auth.getPrincipal();
        return tokenService.allocateToken(userCredentials.getUsername());
    }
}
