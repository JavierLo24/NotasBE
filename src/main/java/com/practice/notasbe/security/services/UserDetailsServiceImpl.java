package com.practice.notasbe.security.services;


import com.practice.notasbe.entities.UserE;
import com.practice.notasbe.exceptions.ItemNotFoundException;
import com.practice.notasbe.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserE userE = userRepository.findUserByEmail(username).orElseThrow();

        List<? extends GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(userE.getRolId().getDescription()));

        return new User(
                userE.getEmail(),
                userE.getPassword(),
                authorities
        );
    }


}
