package com.admin.study.admin.repository;

import com.admin.study.admin.AdminApplicationTests;
import com.admin.study.admin.model.entity.Item;
import com.admin.study.admin.model.enumclass.ItemStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest extends AdminApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create(){
        Item item = new Item();
        item.setStatus(ItemStatus.valueOf("UNREGISTERED"));
        item.setName("Samsung Notebook");
        item.setTitle("Samsung Notebook A100");
        item.setContent("this is a new notebook");
        item.setPrice(BigDecimal.valueOf(900000));
        item.setBrandName("Samsung");
        item.setRegisteredAt(LocalDateTime.now());
        item.setCreatedAt(LocalDateTime.now());
        item.setCreatedBy("Partner01");
        //item.setPartnerId(1L);

        Item newItem = itemRepository.save(item);
        Assertions.assertNotNull(newItem);

    }

    @Test
    public void read(){
        Long id = 1L;
        Optional<Item> item = itemRepository.findById(id);

        Assertions.assertTrue(item.isPresent());

    }
}