package com.admin.study.admin.model.network.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderGroupApiRequest {

    private Long id;

    private String status;

    private String orderType;

    private String revAddress;

    private String revName;

    private String paymentMethod;

    private BigDecimal totalPrice;

    private Integer totalQuantity;

    private LocalDateTime orederAt;

    private LocalDateTime arrivalDate;

    private Long userId;

}