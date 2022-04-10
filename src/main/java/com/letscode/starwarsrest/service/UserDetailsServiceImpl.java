package com.letscode.starwarsrest.service;

import com.letscode.starwarsrest.data.UserDetailsData;
import com.letscode.starwarsrest.model.Login;
import com.letscode.starwarsrest.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Login> login = loginRepository.findByUsername(username);

        if(login.isEmpty()){
            throw new UsernameNotFoundException("Usuário ["+ username +"] não encontrado.");
        }

        return new UserDetailsData(login);
    }

}
