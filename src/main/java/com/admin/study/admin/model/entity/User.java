package com.admin.study.admin.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="user") //if the table name and class name is the same, no need to declare @Table (auto changes camelCase to snake_case)
@ToString(exclude = {"orderGroupList"})
@EntityListeners(AuditingEntityListener.class)
@Builder // Lombok : builder pattern
@Accessors(chain = true)// Lombok : chain pattern
public class User {

    //@Column(name="id")//if the table name and class name is the same, no need to declare @Table (auto changes camelCase to snake_case)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String account;

    private String password;

    private String status;

    private String email;

    private String phoneNumber;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private String updatedBy;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<OrderGroup> orderGroupList;

}
    //LAZY = 지연로딩, EAGER = 즉시로딩
    //LAZY = SELECT * FROM item where id = ?
    //orderDetailList를 호출 하지 않는 이상 조인 하지 않음, 나중에 호출

    //EAGER = JOIN
    //item.id = order_detail.item_id
    //user.id = order_detail.user_id
    //WHERE item_id = ?
    //JOIN후 즉시 모든 것을 로딩함
    //성능저하의 문제가 있으므로 one to one 이나 many to one 관계가 아니니이상 추천하지않음음

