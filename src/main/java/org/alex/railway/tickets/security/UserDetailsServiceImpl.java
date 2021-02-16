package org.alex.railway.tickets.security;

import org.alex.railway.tickets.entity.User;
import org.alex.railway.tickets.entity.enums.RoleType;
import org.alex.railway.tickets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user =  userService.findUserByEmail(email);
        Set<? extends GrantedAuthority> roles = Arrays.stream(RoleType.values()).collect(Collectors.toSet());

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getEmail()
                                                                                            ,user.getPassword()
                                                                                            ,roles);
        return userDetails;
    }
}
