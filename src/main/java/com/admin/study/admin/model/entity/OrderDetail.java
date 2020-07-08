package com.admin.study.admin.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "order_detail")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user","item"})
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderAt;

    //N:1
    @ManyToOne
    private User user; //@ManyToOne automatically find the id of user

    //N:1
    @ManyToOne
    private Item item;
}
