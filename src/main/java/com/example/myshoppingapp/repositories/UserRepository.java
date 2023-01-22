package com.example.myshoppingapp.repositories;

import com.example.myshoppingapp.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndPassword(String username, String password);


    Optional<User> findByEmail (String email);

    Optional<User> findByUsername (String username);

    @Modifying
    @Transactional
    @Query("update User u " +
            "set u.username = :newUsername, "+
            "u.password = :newPassword, " +
            "u.email = :newEmail " +
            "where u.id = :id"
    )
    void updateUser(Long id, String newUsername, String newPassword, String newEmail);
}
