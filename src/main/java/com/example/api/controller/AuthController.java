package com.example.api.controller;

import com.example.api.jwt.JwtUtil;
import com.example.api.model.request.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${security.username}")
    private String username;

    @Value("${security.password}")
    private String password;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthRequest authRequest) {
        // TODO add authenticate user/password here
        boolean isValidAuthReq = checkUserNamePassword(authRequest);
        if (isValidAuthReq) {
            // TODO custom responseEntity here to return wanted model
            return new ResponseEntity<>(jwtUtil.generateToken(authRequest.getUsername()), HttpStatus.OK);
        }
        return new ResponseEntity<>( "not valid username/password", HttpStatus.OK);
    }

    private boolean checkUserNamePassword(AuthRequest authRequest){
        return username.equals(authRequest.getUsername()) && password.equals(authRequest.getPassword());
    }
}

