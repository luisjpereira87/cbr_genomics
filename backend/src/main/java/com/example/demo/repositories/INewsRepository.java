package com.example.demo.repositories;

import com.example.demo.models.News;
import com.example.demo.models.Users;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INewsRepository extends JpaRepository<News, Long> {

  Optional<News> findByIdAndUser(Long id, Users user);

  Optional<List<News>> findByIsPublish(Boolean isPublish);

  Optional<News> findByIdAndIsPublish(Long id, Boolean isPublish);
}
