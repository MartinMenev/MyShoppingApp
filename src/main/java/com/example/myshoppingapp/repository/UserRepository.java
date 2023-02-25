package com.example.myshoppingapp.repository;

<<<<<<< HEAD
import com.example.myshoppingapp.domain.users.UserEntity;
=======
import com.example.myshoppingapp.domain.users.User;
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
<<<<<<< HEAD
public interface UserRepository extends JpaRepository<UserEntity, Long> {



    Optional<UserEntity> findByEmail (String email);

    Optional<UserEntity> findUserEntityByUsername (String username);

    @Modifying
    @Transactional
    @Query("update UserEntity u " +
=======
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndPassword(String username, String password);


    Optional<User> findByEmail (String email);

    Optional<User> findByUsername (String username);

    @Modifying
    @Transactional
    @Query("update User u " +
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7
            "set u.username = :newUsername, "+
            "u.password = :newPassword, " +
            "u.email = :newEmail " +
            "where u.id = :id"
    )
    void updateUser(Long id, String newUsername, String newPassword, String newEmail);

<<<<<<< HEAD
=======


>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7
}
