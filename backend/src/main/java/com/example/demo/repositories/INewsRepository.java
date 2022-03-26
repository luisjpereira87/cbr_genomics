package com.example.demo.repositories;

import com.example.demo.models.News;
import com.example.demo.models.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {

  Optional<News> findByIdAndUser(Long id, Users user);

}
