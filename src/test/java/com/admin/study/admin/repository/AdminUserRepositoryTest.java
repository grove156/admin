package com.admin.study.admin.repository;

import com.admin.study.admin.AdminApplicationTests;
import com.admin.study.admin.model.entity.AdminUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import static org.junit.jupiter.api.Assertions.*;

class AdminUserRepositoryTest extends AdminApplicationTests {
    @Autowired
    private AdminUserRepository adminUserRepository;

    @Test
    public void create(){
        AdminUser adminUser = new AdminUser();

        adminUser.setAccount("Admin01");
        adminUser.setPassword("admin01");
        adminUser.setStatus("REGISTERED");
        adminUser.setRole("PARTNER");

        AdminUser newAdminUser = adminUserRepository.save(adminUser);

        adminUser.setStatus("CHANGED");
        adminUserRepository.save(adminUser);
    }
}