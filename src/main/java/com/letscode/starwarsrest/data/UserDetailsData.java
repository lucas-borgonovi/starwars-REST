package com.letscode.starwarsrest.data;

import com.letscode.starwarsrest.model.Login;
import com.letscode.starwarsrest.model.Roles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;
import java.util.*;

public class UserDetailsData implements UserDetails {

    private final Optional<Login> login;

    public UserDetailsData(Optional<Login> login) {
        this.login = login;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> listRoles = new ArrayList<GrantedAuthority>();

        listRoles.add(new SimpleGrantedAuthority("ROLE_" + login.get().getRoles().toString()));
        return listRoles;
    }

    @Override
    public String getPassword() {
        return login.orElse(new Login()).getPassword();
    }

    @Override
    public String getUsername() {
        return login.orElse(new Login()).getUsername();
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
