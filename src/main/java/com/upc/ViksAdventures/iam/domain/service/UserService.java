package com.upc.ViksAdventures.iam.domain.service;

import com.upc.ViksAdventures.iam.domain.model.Role;
import com.upc.ViksAdventures.iam.domain.model.User;
import java.util.List;

public interface UserService {
    List<User> getAll();
    User getById(Long userId);
    User create(User user);
    User update(Long userId, User user);
    User assignRole(User user, Role role);
    void delete(Long userId);
}