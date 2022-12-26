package com.example.myshoppingapp.repositories;

import com.example.myshoppingapp.models.users.LoginDTO;
import com.example.myshoppingapp.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndPassword(String username, String password);


    Optional<User> findByEmail (String email);
}
