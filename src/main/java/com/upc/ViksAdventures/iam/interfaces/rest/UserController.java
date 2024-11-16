package com.upc.ViksAdventures.iam.interfaces.rest;

import com.upc.ViksAdventures.iam.domain.model.User;
import com.upc.ViksAdventures.iam.domain.service.UserService;
import com.upc.ViksAdventures.iam.interfaces.rest.mapping.UserMapping;
import com.upc.ViksAdventures.iam.interfaces.rest.resource.SignUpUserResource;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserMapping userMapping;

    public UserController(UserService userService, UserMapping userMapping) {
        this.userService = userService;
        this.userMapping = userMapping;
    }

    @GetMapping
    public ResponseEntity<List<SignUpUserResource>> getAllUsers() {
        List<User> users = userService.getAll();
        List<SignUpUserResource> resources = users.stream()
                .map(userMapping::toSignUpResource)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<SignUpUserResource> getUserById(@PathVariable Long userId) {
        User user = userService.getById(userId);
        return ResponseEntity.ok(userMapping.toSignUpResource(user));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<SignUpUserResource> updateUser(
            @PathVariable Long userId,
            @Valid @RequestBody SignUpUserResource signUpUserResource) {
        User user = userMapping.toModel(signUpUserResource);
        User updatedUser = userService.update(userId, user);
        return ResponseEntity.ok(userMapping.toSignUpResource(updatedUser));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        userService.delete(userId);
        return ResponseEntity.ok().build();
    }
}