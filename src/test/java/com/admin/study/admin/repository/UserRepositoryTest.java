package com.admin.study.admin.repository;

import com.admin.study.admin.AdminApplicationTests;
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
        User user = new User();
        user.setAccount("TestUser03");
        user.setEmail("TestUser03@gmail.com");
        user.setPhoneNumber("010-2222-1111");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("admin");

        User newUser = userRepository.save(user);

        System.out.println("new user: " + newUser);
    }

    @Test
    public User read(){
         Optional<User> user = userRepository.findById(1L);

         user.ifPresent(selectedUser->{
             System.out.println(selectedUser);
             System.out.println(selectedUser.getEmail());
         });

         return user.get();
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