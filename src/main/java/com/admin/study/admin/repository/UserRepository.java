package com.admin.study.admin.repository;

import com.admin.study.admin.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //SELECT * FROM user WHERE account = ? <<test03
    Optional<User> findByAccount(String account);

    //SELECT * FROM user WHERE email = ?
    Optional<User> findByEmail(String email);

    //SELECT * FROM user WHERE account = ? AND email = ?
    Optional<User> findByAccountAndEmail(String account, String email);
}
