package com.f5.Airline.users;


import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> index(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(userService.getAllPaginated(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> show(@PathVariable Long id) {
        return userService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDto newUser) {
        if (userService.existsByEmail(newUser.email())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El email ya existe");
        }
        User user = userService.store(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDto updatedUser) {
        return userService.update(id, updatedUser)
                .<ResponseEntity<?>>map(user -> ResponseEntity.ok(user)) // Aseguramos que el tipo sea ResponseEntity<?>
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no existe"));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        if (!userService.delete(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no existe");
        }
        return ResponseEntity.noContent().build();
    }
}
*/
    // Registro de usuario
   /* @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User newUser = userService.registerUser(user);
        return ResponseEntity.ok(newUser);
    }*/


