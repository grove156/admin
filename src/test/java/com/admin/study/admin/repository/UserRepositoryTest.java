package com.admin.study.admin.repository;

import com.admin.study.admin.AdminApplicationTests;
import com.admin.study.admin.model.entity.Item;
import com.admin.study.admin.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest extends AdminApplicationTests {
    //Dependency Injection
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){
        String account = "Test01";
        String password = "Test01";
        String status = "REGISTERED";
        String email = "Test01@gmail.com";
        String phoneNumber = "010-1111-2222";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);
        user.setCreatedAt(createdAt);
        user.setCreatedBy(createdBy);

        User newUser = userRepository.save(user);

        Assertions.assertNotNull(newUser);

    }

    @Transactional
    @Test
    public void read(){
        String phoneNumber = "010-1111-2222";
        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc(phoneNumber);
        Assertions.assertNotNull(user);
    }

    @Test
    public void update(){
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectedUser->{
            selectedUser.setAccount("TestUser2");
            selectedUser.setUpdatedAt(LocalDateTime.now());
            selectedUser.setUpdatedBy("Admin");

            userRepository.save(selectedUser);
        });
    }

    @Test
    @Transactional //run all the way down but at the end it dosent excute so the data wouldnt be createed,updated,deleted (roll back transaction)
    public void delete(){
        Optional<User> user = userRepository.findById(3L);

        Assertions.assertTrue(user.isPresent());

        user.ifPresent(selectedUser->{
            userRepository.delete(selectedUser);
        });

        Optional<User> deletedUser = userRepository.findById(3L);

        Assertions.assertFalse(deletedUser.isPresent());
    }
}