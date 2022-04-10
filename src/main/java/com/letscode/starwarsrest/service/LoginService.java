package com.letscode.starwarsrest.service;

import com.letscode.starwarsrest.model.Login;
import com.letscode.starwarsrest.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LoginService {

    private LoginRepository loginRepository;

    public Login getLoginByName(String username){

        Optional<Login> user = loginRepository.findByUsername(username);

        if(user.isPresent()){
            return user.get();
        }

        return null;

    }



}
