package com.example.demo.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "users")
@Setter
@Getter
public class User {

  @ManyToMany(cascade = {CascadeType.ALL})
  @JoinTable(
      name = "votes",
      joinColumns = {@JoinColumn(name = "user_id")},
      inverseJoinColumns = {@JoinColumn(name = "new_id")}
  )
  Set<News> votes = new HashSet<>();
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column(name = "username", length = 255, nullable = false, unique = false)
  private String username;
  @Column(name = "password", length = 255, nullable = false, unique = false)
  private String password;
  @Column(name = "role", length = 255, nullable = false, unique = false)
  private String role;
  @OneToMany(mappedBy = "user")
  private Set<News> news;
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

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