package com.f5.Airline.users;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api-endpoint}/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Obtener todos los users
@GetMapping
    public List<User> index() {
        return userService.getAll();
    }

    // Obtener un país por ID
    @GetMapping("/{id}")
    public ResponseEntity<User> show(@PathVariable("id") Long id) {
       User user = userService.getById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    //crear nuevo usuario
@PostMapping
    public ResponseEntity<?>createUser(@RequestBody User newUser) {
        boolean existsByEmail = userService.existsByEmail(newUser.getEmail());
        if (existsByEmail) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Email already exists");
        }
        User user = userService.store(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
}
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody User updatedUser) {
        // Buscar el país existente por ID
        User existingUser = userService.getById(id);

        // Si el usuario no existe, devolver un 404 Not Found
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario con ID " + id + " no existe.");
        }

        // Actualizar los campos del usuario (excepto el ID)
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        // Puedes agregar más campos si es necesario

        // Guardar los cambios
        User savedUser = userService.update(existingUser);

        // Devolver el país actualizado con un 200 OK
        return ResponseEntity.ok(savedUser);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario con ID " + id + " no existe.");
        }
        userService.delete(id);
        return ResponseEntity.noContent().build(); // 204 - Sin contenido
    }



}
