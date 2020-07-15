package com.admin.study.admin.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ItemStatus {

    REGISTERED(0,"등록","상품 등록 상태"),
    UNREGISTERED(1,"해지","상품 등록 해지"),
    WAITING(2,"대기","상품 등록 대기");

    private Integer id;
    private String title;
    private String description;
}
