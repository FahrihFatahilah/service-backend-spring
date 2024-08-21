package com.dans.jobs.controller;


import com.dans.jobs.entity.Login;
import com.dans.jobs.entity.LoginResponse;
import com.dans.jobs.entity.RegisterUser;
import com.dans.jobs.entity.User;
import com.dans.jobs.service.AuthenticationService;
import com.dans.jobs.service.JwtService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    @Operation(tags = "no-auth", description = "end point for register user")
    public ResponseEntity<User> register(@RequestBody RegisterUser request) throws Exception {
        logger.info(">>> incoming register user");
        User registeredUser = authenticationService.signup(request);

        logger.info(">>> user register request {} ",request);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    @Operation(tags = "no-auth",description = "end point for user login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody Login request) throws JsonProcessingException {
        logger.info(">>> incoming login");

        User authenticatedUser = authenticationService.authenticate(request);

        String jwtToken = jwtService.generateToken((UserDetails) authenticatedUser);

        logger.info(">>> user login request {} / token :  {} ",authenticatedUser,jwtToken);


        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setJwtToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
//        loginResponse.setUsername();
        return ResponseEntity.ok(loginResponse);
    }
}