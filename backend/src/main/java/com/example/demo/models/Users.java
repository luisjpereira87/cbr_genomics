package com.example.demo.models;

import com.example.demo.dto.UsersDTO;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "users")
@Setter
@Getter
@NoArgsConstructor
public class Users {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column(name = "username", length = 255, nullable = false, unique = false)
  private String username;
  @Column(name = "password", length = 255, nullable = false, unique = false)
  private String password;
  @Column(name = "role", length = 255, nullable = false, unique = false)
  private String role;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  public Users(UsersDTO usersDTO) {
    this.setUsername(usersDTO.getUsername());
    this.setPassword(usersDTO.getPassword());
    this.setRole("ROLE_USER");
  }

  @PrePersist
  private void prePersist() {
    this.setCreatedAt(LocalDateTime.now());
    this.setUpdatedAt(LocalDateTime.now());
  }

  @PreUpdate
  private void preUpdate() {
    this.setUpdatedAt(LocalDateTime.now());
  }

}