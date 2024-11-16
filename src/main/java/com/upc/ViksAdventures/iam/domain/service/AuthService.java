package com.upc.ViksAdventures.iam.domain.service;

import com.upc.ViksAdventures.iam.domain.model.User;

public interface AuthService {
    String signIn(String email, String password);
    User registerLocalUser(User user);
}