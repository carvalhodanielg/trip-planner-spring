package com.example.todo.services;

import com.example.todo.dto.request.LoginRequest;
import com.example.todo.dto.request.RegisterRequest;
import com.example.todo.dto.request.UpdateUserRequest;
import com.example.todo.dto.response.UserResponse;
import com.example.todo.entities.User;
import com.example.todo.repositories.UserRepository;
import com.example.todo.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserResponse register (RegisterRequest request) {
        var user = User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .build();

    userRepository.save(user);
    return new UserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getBio(),user.getEmail());
    }

    public String login (LoginRequest request) {
        var user = userRepository.findByEmail(request.email()).orElseThrow(() -> new RuntimeException("User not found"));

        if(!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Incorrect password");
        }

        return jwtService.generateToken(user);
    }

    public List<UserResponse> getAllUsers(){
        return userRepository.findAll().stream()
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getBio(),
                        user.getEmail()
                )).toList();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }


    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, UpdateUserRequest updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setFirstName(updatedUser.firstName());
                    user.setLastName(updatedUser.lastName());
                    user.setEmail(updatedUser.email());
                    user.setBio(updatedUser.bio());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found1"));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }



}
