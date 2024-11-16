package com.upc.ViksAdventures.iam.interfaces.rest;

import com.upc.ViksAdventures.iam.domain.model.User;
import com.upc.ViksAdventures.iam.domain.service.AuthService;
import com.upc.ViksAdventures.iam.interfaces.rest.mapping.UserMapping;
import com.upc.ViksAdventures.iam.interfaces.rest.resource.SignInUserResource;
import com.upc.ViksAdventures.iam.interfaces.rest.resource.SignUpUserResource;
import com.upc.ViksAdventures.iam.interfaces.rest.resource.AuthenticatedUserResource;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;
    private final UserMapping userMapping;

    public AuthController(AuthService authService, UserMapping userMapping) {
        this.authService = authService;
        this.userMapping = userMapping;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpUserResource signUpUserResource) {
        try {
            User user = userMapping.toModel(signUpUserResource);
            User createdUser = authService.registerLocalUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(userMapping.toSignUpResource(createdUser));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User registration failed: " + e.getMessage());
        }
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody SignInUserResource signInUserResource) {
        try {
            String token = authService.signIn(signInUserResource.getEmail(), signInUserResource.getPassword());
            AuthenticatedUserResource authenticatedUserResource = new AuthenticatedUserResource(
                    signInUserResource.getEmail(), token
            );
            return ResponseEntity.ok(authenticatedUserResource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed: " + e.getMessage());
        }
    }
}