package ua.com.alevel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.alevel.entity.User;

public interface UserRepository extends JpaRepository<User, Long> { }
