package com.example.demo.services;

import com.example.demo.dto.UsersDTO;
import com.example.demo.models.Users;
import com.example.demo.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IUserRepository userRepository;

    /**
     * Save new user
     *
     * @param usersDTO
     * @return
     */
    public Users save(UsersDTO usersDTO) {
        usersDTO.setPassword(passwordEncoder.encode(usersDTO.getPassword()));
        return this.userRepository.save(new Users(usersDTO));
    }
}
