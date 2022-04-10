package com.letscode.starwarsrest.controller;

import com.letscode.starwarsrest.model.Login;
import com.letscode.starwarsrest.model.Rebelde;
import com.letscode.starwarsrest.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("create")
public class UserLoginController {

    private final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping()
    public ResponseEntity createUser(@RequestBody Login login){

        login.setPassword(passwordEncoder.encode(login.getPassword()));

        return ResponseEntity.ok().body(loginRepository.save(login));


    }


}
