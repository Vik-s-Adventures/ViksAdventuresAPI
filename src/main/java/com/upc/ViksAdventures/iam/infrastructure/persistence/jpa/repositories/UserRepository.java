package com.upc.ViksAdventures.iam.infrastructure.persistence.jpa.repositories;

import com.upc.ViksAdventures.iam.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    Optional<User> findByEmail(String email);
}
