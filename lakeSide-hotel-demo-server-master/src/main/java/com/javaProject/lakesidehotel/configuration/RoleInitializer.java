package com.javaProject.lakesidehotel.configuration;

import com.javaProject.lakesidehotel.model.Role;
import com.javaProject.lakesidehotel.model.User;
import com.javaProject.lakesidehotel.repository.RoleRepository;
import com.javaProject.lakesidehotel.repository.UserRepository;
import com.javaProject.lakesidehotel.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;

@Configuration
public class RoleInitializer {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    private static final org.slf4j.Logger log = LoggerFactory.getLogger(RoleInitializer.class);


//    @PostConstruct
    public void initDefaultRoles() {
        List<String> defaultRoleNames = Arrays.asList("ROLE_USER", "ROLE_ADMIN");
        defaultRoleNames.forEach(roleName -> {
            if (!roleRepository.existsByName(roleName)) {
                roleRepository.save(new Role(roleName));
            }
        });
    }


    @Transactional
    @PostConstruct
    public void initAdmin() {

        // Check for existing admin users:
        if (!userRepository.existsByRoles_Name("ROLE_ADMIN")) {

            // Retrieve the "ROLE_ADMIN" role, creating it only if it doesn't exist:
            Role adminRole = roleRepository.findByName("ROLE_ADMIN").
                    orElseGet(() -> roleRepository.save(new Role("ROLE_ADMIN")));

            User defaultAdmin = new User("admin", "admin", "admin@gmail.com",
                    passwordEncoder.encode("Admin@123"),
                    Collections.singletonList(adminRole));
            // Save the default admin user:
            try {
                userRepository.save(defaultAdmin);
                log.info("Default admin user created successfully.");
            } catch (Exception e) {
                log.error("Failed to create default admin user: {}", e.getMessage());
            }
            finally {
                this.initDefaultRoles();
            }
        } else {
            log.info("Admin user(s) already exist, no default admin creation needed.");
        }
    }
}