package com.admin.study.admin.model;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchParam {
    private String account;

    private String email;

    private int page;
}
