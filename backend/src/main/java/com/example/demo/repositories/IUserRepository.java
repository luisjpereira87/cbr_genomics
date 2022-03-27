package com.example.demo.repositories;

import com.example.demo.models.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<Users, Long> {

  Optional<Users> findByUsername(String username);
}
