package com.dans.jobs.service;

import com.dans.jobs.entity.Login;
import com.dans.jobs.entity.RegisterUser;
import com.dans.jobs.entity.User;
import com.dans.jobs.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUser input) throws Exception {
        User user = new User();
        user.setNama(input.getNama());
        user.setUsername(input.getUsername());
        user.setId_pangkat(input.getId_pangkat());
        user.setId_jabatan(input.getId_jabatan());
        user.setNip(input.getNip());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        Optional<User> isexist = userRepository.findByNip(input.getNip());
        if(isexist.isPresent()){
            throw new Exception("user already Exist");
        }

        return userRepository.save(user);
    }

    public User getToken(RegisterUser input) throws Exception {
        User user = new User();
        user.setNama(input.getNama());
        user.setUsername(input.getUsername());
        user.setId_pangkat(input.getId_pangkat());
        user.setId_jabatan(input.getId_jabatan());
        user.setNip(input.getNip());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        Optional<User> isexist = userRepository.findByNip(input.getNip());
        if(isexist.isPresent()){
            throw new Exception("user already Exist");
        }

        return userRepository.save(user);
    }

    public User authenticate(Login input) throws JsonProcessingException {
        System.out.println("is valid");
        ObjectMapper objectMapper = new ObjectMapper();
        String a = objectMapper.writeValueAsString(input);
        System.out.println("dataa : " + a);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getNip(),
                        input.getPassword()
                )
        );

        return userRepository.findByNip(input.getNip())
                .orElseThrow();
    }
}