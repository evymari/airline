package com.f5.Airline.users;
/*
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllPaginated(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public User store(UserDto userDto) {
        User user = new User(userDto.username(), passwordEncoder.encode(userDto.password()), userDto.email());
        return userRepository.save(user);
    }

    public Optional<User> update(Long id, UserDto userDto) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(userDto.username());
            user.setEmail(userDto.email());
            user.setPassword(passwordEncoder.encode(userDto.password()));
            return userRepository.save(user);
        });
    }

    public boolean delete(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}*/
/*
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public User store(User user) {
        return userRepository.save(user);
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}*/
/*
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public User getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public User store(User newUser) {
        return repository.save(newUser);
    }

    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    public User update(User user) {
        return repository.save(user);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}*/

