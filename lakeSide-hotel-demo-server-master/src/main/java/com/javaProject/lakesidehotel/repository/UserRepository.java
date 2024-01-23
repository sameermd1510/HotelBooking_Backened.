package com.javaProject.lakesidehotel.repository;

import com.javaProject.lakesidehotel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Mohammad Sameer
 */

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    void deleteByEmail(String email);

   Optional<User> findByEmail(String email);

    boolean existsByRoles_Name(String name);
}
