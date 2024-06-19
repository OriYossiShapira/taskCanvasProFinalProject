package com.ori.taskcanvaspro.model.repository;

import com.ori.taskcanvaspro.model.entity.Task;
import com.ori.taskcanvaspro.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    User findByEmailAndPassword(String email, String Password);
    Optional<User> findByEmail(String email);

}
