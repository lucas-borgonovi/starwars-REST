package com.letscode.starwarsrest.core;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.letscode.starwarsrest.data.UserDetailsData;
import com.letscode.starwarsrest.model.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@RequiredArgsConstructor
public class JWTAuthenticateFilter extends UsernamePasswordAuthenticationFilter {

    public static final int TOKEN_EXPIRES = 600_000;

    public static final String TOKEN_SENHA = "StarWarsSecretKey";


    private final AuthenticationManager authenticationManager;




    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {


        try {
            Login login = new ObjectMapper().readValue(request.getInputStream(), Login.class);

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    login.getUsername(),
                    login.getPassword(),
                    new ArrayList<>()
            ));



        } catch (IOException e) {
            throw new RuntimeException("Falha ao autenticar usuario",e);
        }


    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {


        UserDetailsData userData = (UserDetailsData) authResult.getPrincipal();

        Collection<? extends GrantedAuthority> roles = userData.getAuthorities();

        String rolesArray = roles.toArray()[0].toString();

        String token = JWT.create()
                .withSubject(userData.getUsername())
                .withClaim("Roles", rolesArray)
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRES))
                .sign(Algorithm.HMAC512(TOKEN_SENHA));


        response.getWriter().write(token);
        response.getWriter().flush();




    }
}
