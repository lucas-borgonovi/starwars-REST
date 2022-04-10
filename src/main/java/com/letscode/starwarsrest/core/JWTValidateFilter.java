package com.letscode.starwarsrest.core;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JWTValidateFilter extends BasicAuthenticationFilter {


    public static final String HEADER_ATTRIBUTE = "Authorization";
    public static final String PREFIX = "Bearer ";



    public JWTValidateFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {


        String attribute =  request.getHeader(HEADER_ATTRIBUTE);

        if (attribute == null){
            chain.doFilter(request,response);
            return;
        }

        if(!attribute.startsWith(PREFIX)){
            chain.doFilter(request,response);
            return;
        }

        String token = attribute.replace(PREFIX,"");

        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(token);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        chain.doFilter(request,response);


    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token){


        String user = JWT.require(Algorithm.HMAC512(JWTAuthenticateFilter.TOKEN_SENHA))
                .build()
                .verify(token)
                .getSubject();

        var role = JWT.require(Algorithm.HMAC512(JWTAuthenticateFilter.TOKEN_SENHA))
                .build()
                .verify(token)
                .getClaim("Roles");

        List<GrantedAuthority> listRoles = new ArrayList<GrantedAuthority>();

        String teste = role.asString();
        String teste2 = role.toString();


        listRoles.add(new SimpleGrantedAuthority(role.asString()));


        if(user == null){
            return null;
        }
        return new UsernamePasswordAuthenticationToken(user,null,listRoles);

    }


}
