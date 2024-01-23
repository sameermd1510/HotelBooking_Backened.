package com.javaProject.lakesidehotel.service;

import com.javaProject.lakesidehotel.exception.UserAlreadyExistsException;
import com.javaProject.lakesidehotel.model.Role;
import com.javaProject.lakesidehotel.model.User;
import com.javaProject.lakesidehotel.repository.RoleRepository;
import com.javaProject.lakesidehotel.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author Mohammad Sameer
 */

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Transactional
    @Override
    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())){
            throw new UserAlreadyExistsException(user.getEmail() + " already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user.getPassword());

        /*

        // old Implementation..give error if WserRole is Not Present.
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        user.setRoles(Collections.singletonList(userRole));
        */
        roleRepository.findByName("ROLE_USER")
                .ifPresentOrElse(
                        userRole -> user.setRoles(Collections.singletonList(userRole)),
                        () -> {
                            // Role not found, create it
                            Role newRole = new Role("ROLE_USER");
                            roleRepository.save(newRole);
                            user.setRoles(Collections.singletonList(newRole));
                        }
                );
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteUser(String email) {
        User theUser = getUser(email);
        if (theUser != null){
            userRepository.deleteByEmail(email);
        }

    }

    @Override
    public User getUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
