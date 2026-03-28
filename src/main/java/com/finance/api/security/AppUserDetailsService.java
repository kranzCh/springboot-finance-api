package com.finance.api.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Placeholder UserDetailsService — replace with a repository-backed implementation
 * once the User entity and UserRepository are created.
 */
@Service
public class AppUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO: replace with: userRepository.findByEmail(username)
        //         .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        throw new UsernameNotFoundException("User not found: " + username);
    }
}
