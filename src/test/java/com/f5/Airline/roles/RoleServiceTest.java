package com.f5.Airline.roles;

import com.f5.Airline.exceptions.RoleNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class RoleServiceTest {


    private RoleRepository repository;
    private RoleService service;

    @BeforeEach
    void setUp() {
        repository = mock(RoleRepository.class);
        service = new RoleService(repository);
    }



    @Test
    void getById_shouldThrowException_whenRoleDoesNotExist() {
        when(repository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(RoleNotFoundException.class, () -> service.getById(2L));
    }

    @Test
    void getByName_shouldReturnRole_whenRoleExists() throws RoleNotFoundException {
        Role role = new Role();
        role.setName("ROLE_USER");

        List<Role> roles = List.of(role);
        when(repository.findAll()).thenReturn(roles);

        Role result = service.getByName("ROLE_USER");

        assertEquals("ROLE_USER", result.getName());
    }

    @Test
    void getByName_shouldThrowException_whenRoleNotFound() {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(RoleNotFoundException.class, () -> service.getByName("ROLE_ADMIN"));
    }

    @Test
    void assignDefaultRole_shouldReturnSetWithDefaultRole() {
        Role defaultRole = new Role();
        defaultRole.setName("ROLE_USER");

        when(repository.findAll()).thenReturn(List.of(defaultRole));

        Set<Role> roles = service.assignDefaultRole();

        assertEquals(1, roles.size());
        assertTrue(roles.stream().anyMatch(r -> r.getName().equals("ROLE_USER")));
    }
}
