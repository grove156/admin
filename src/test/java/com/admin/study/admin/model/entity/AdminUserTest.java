package com.admin.study.admin.model.entity;

import com.admin.study.admin.AdminApplicationTests;
import com.admin.study.admin.repository.AdminUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AdminUserTest extends AdminApplicationTests {
    @Autowired
    private AdminUserRepository adminUserRepository;

    @Test
    public void create(){
        AdminUser adminUser = new AdminUser();

        adminUser.setAccount("AdminUser01");
        adminUser.setPassword("AdminUser01");
        adminUser.setStatus("REGISTERED");
        adminUser.setRole("PARTNER");
        adminUser.setCreatedAt(LocalDateTime.now());
        adminUser.setCreatedBy("AdminServer");

        AdminUser newAdminUser = adminUserRepository.save(adminUser);
        assertNotNull(newAdminUser);
        
    }


}