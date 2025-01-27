package com.f5.Airline.roles;

import com.f5.Airline.roles.exceptions.RoleNotFoundException;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.Set;

@Service
public class RoleService {
    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public Role getById(Long id) throws RoleNotFoundException {
        Role role = repository.findById(id).orElseThrow( () -> new RoleNotFoundException("Role not found"));
        return role;
    }

    public Set<Role> assignDefaultRole() {
        Role defaultRole = this.getByName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(defaultRole);
        return roles;
    }

    public Role getByName(String name) throws RoleNotFoundException {
        return repository.findAll().stream()
                .filter(role -> role.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new RoleNotFoundException("Role not found: " + name));
    }


}

