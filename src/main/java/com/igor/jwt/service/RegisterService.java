package com.igor.jwt.service;

import com.igor.jwt.dtos.RegisterDTO;
import com.igor.jwt.model.Role;
import com.igor.jwt.model.UserCredentials;
import com.igor.jwt.repository.RoleRepository;
import com.igor.jwt.repository.UserCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RegisterService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    @Transactional
    public void register(RegisterDTO registerDTO) {
        var userCredentialsOptional = userCredentialsRepository.findByUsername(registerDTO.email());
        if(userCredentialsOptional.isPresent()){
            throw new RuntimeException("Usuário já existe");
        }
        Role role = roleRepository.findById(registerDTO.roleId()).orElseThrow(
                ()->new RuntimeException("Role não existe")
        );
        var userCredentials = new UserCredentials(null, registerDTO.email(),
                        passwordEncoder.encode(registerDTO.password()),role);
        userCredentialsRepository.save(userCredentials);
    }
}
