package com.admin.study.admin.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderType {

    ALL(0,"전체 배송","전부 배송 합니다."),
    EACH(1,"개별 배송","개별적 으로 배송 합니다.");

    private Integer id;
    private String title;
    private String description;
}
