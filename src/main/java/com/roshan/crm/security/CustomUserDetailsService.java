package com.roshan.crm.security;

import com.roshan.crm.entity.User;
import com.roshan.crm.repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(
            UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(
            String email)
            throws UsernameNotFoundException {

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User Not Found"));

        // Strip "ROLE_" prefix if it exists in the database to prevent IllegalArgumentException
        // since .roles() automatically adds the prefix.
        String role = user.getRole();
        if (role != null && role.startsWith("ROLE_")) {
            role = role.substring(5);
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(role != null ? role : "USER")
                .build();
    }
}
