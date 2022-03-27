package com.example.demo.controllers;

import com.example.demo.dto.UsersDTO;
import com.example.demo.models.Users;
import com.example.demo.services.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@SecurityRequirement(name = "cbr_genomics")
public class UserController {

    @Autowired
    private UsersService usersService;

    @PostMapping
    @ResponseBody
    @Operation(summary = "Create new user", description = "Create new user with user permissions")
    public ResponseEntity<Users> create(@RequestBody UsersDTO usersDTO) {

        return ResponseEntity.ok(this.usersService.save(usersDTO));
    }

}
