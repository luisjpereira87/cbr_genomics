package com.example.demo.services;

import com.example.demo.constants.MessagesContants;
import com.example.demo.exceptions.CustomException;
import com.example.demo.models.UserDetailsCustom;
import com.example.demo.models.Users;
import com.example.demo.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        try {
            Users user = this.userRepository.findByUsername(s)
                    .orElseThrow(
                            () -> new CustomException(HttpStatus.NOT_FOUND.ordinal(), MessagesContants.NOT_FOUND,
                                    MessagesContants.USER_NOT_FOUND));

            return new UserDetailsCustom(user);
        } catch (CustomException e) {
            return null;
        }

    }
}