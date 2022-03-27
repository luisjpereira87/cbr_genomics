package com.example.demo.models;

import com.example.demo.dto.NewsDTO;
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
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "news")
@Setter
@Getter
@NoArgsConstructor
public class News {

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "votes", joinColumns = { @JoinColumn(name = "news_id") }, inverseJoinColumns = {
            @JoinColumn(name = "user_id") })
    Set<Users> votes = new HashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "title", length = 255, nullable = false, unique = false)
    private String title;
    @Column(name = "text", length = 400, nullable = false, unique = false)
    private String text;
    @Column(name = "is_publish")
    private Boolean isPublish;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public News(NewsDTO newsDTO, Users user) {
        this.setText(newsDTO.getText());
        this.setTitle(newsDTO.getTitle());
        this.setIsPublish(newsDTO.getIsPublish());
        this.setUser(user);
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
