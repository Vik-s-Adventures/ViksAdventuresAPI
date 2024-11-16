package com.upc.ViksAdventures.iam.application.internal;

import com.upc.ViksAdventures.iam.domain.exception.InvalidCredentialsException;
import com.upc.ViksAdventures.iam.domain.model.Role;
import com.upc.ViksAdventures.iam.domain.model.User;
import com.upc.ViksAdventures.iam.domain.service.AuthService;
import com.upc.ViksAdventures.iam.infrastructure.authorization.JwtTokenProvider;
import com.upc.ViksAdventures.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String signIn(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new InvalidCredentialsException("Invalid credentials"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        return jwtTokenProvider.generateToken(user.getEmail());
    }

    @Override
    public User registerLocalUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(Role.USER));
        return userRepository.save(user);
    }
}