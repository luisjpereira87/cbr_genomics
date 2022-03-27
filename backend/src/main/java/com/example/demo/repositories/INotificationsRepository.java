package com.example.demo.repositories;

import com.example.demo.models.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INotificationsRepository extends JpaRepository<Notifications, Long> {

}