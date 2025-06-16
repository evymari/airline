package com.f5.Airline.security;

import com.f5.Airline.roles.Role;
import com.f5.Airline.users.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;

public class SecurityUser implements UserDetails {

    private final User user;

    public SecurityUser(User user) {
        this.user = user;
    }

    // Cambiar getEmail() por getUsername()
    @Override
    public String getUsername() {
        return user.getEmail();  // Usa email como identificador de usuario
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : user.getRoles()) {
            System.out.println("User role: " + role.getName());
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Cambiar según lógica de negocio si es necesario
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Cambiar según lógica de negocio si es necesario
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Cambiar según lógica de negocio si es necesario
    }

    @Override
    public boolean isEnabled() {
        return true; // Cambiar según lógica de negocio si es necesario
    }
}
