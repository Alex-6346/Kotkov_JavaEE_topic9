package com.example.demo.repository;

import com.example.demo.entity.BookDto;
import com.example.demo.entity.PermissionEntity;
import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Query("SELECT user FROM UserEntity user "
            + "LEFT JOIN FETCH user.permissions "
            + "WHERE user.login = :login")
    Optional<UserEntity> findByLogin(@Param("login") String login);

    @Transactional
    @Modifying
    @Query("UPDATE UserEntity user " +
            "SET user.password = :encodedPassword " +
            "WHERE user.password='password'")
    void encodePasswords(@Param("encodedPassword") String encodedPassword);

    @Query("SELECT per FROM PermissionEntity per "
            + "WHERE per.permission ='VIEW_CATALOG'")
    Optional<PermissionEntity> viewCatalogPermission();

    UserEntity saveAndFlush(UserEntity user);
}

