package com.admin.study.admin.service;

import com.admin.study.admin.ifs.CrudInterface;
import com.admin.study.admin.model.entity.OrderGroup;
import com.admin.study.admin.model.network.Header;
import com.admin.study.admin.model.network.request.OrderGroupApiRequest;
import com.admin.study.admin.model.network.response.OrderGroupApiResponse;
import com.admin.study.admin.repository.OrderGroupRepository;
import com.admin.study.admin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderGroupApiLogicService implements CrudInterface<OrderGroupApiRequest, OrderGroupApiResponse> {
    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Header<OrderGroupApiResponse> create(Header<OrderGroupApiRequest> request) {
        OrderGroupApiRequest body = request.getData();

        OrderGroup orderGroup = OrderGroup.builder()
                .status(body.getStatus())
                .orderType(body.getOrderType())
                .revAddress(body.getRevAddress())
                .revName(body.getRevName())
                .paymentType(body.getOrderType())
                .totalPrice(body.getTotalPrice())
                .totalQuantity(body.getTotalQuantity())
                .orderAt(LocalDateTime.now())
                .user(userRepository.getOne(body.getUserId()))
                .build();

        OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);
        return response(newOrderGroup);
    }

    @Override
    public Header<OrderGroupApiResponse> read(Long id) {
         return orderGroupRepository.findById(id)
                .map(orderGroup -> response(orderGroup))
                .orElseGet(()->Header.Error("NO DATA"));
    }

    @Override
    public Header<OrderGroupApiResponse> update(Header<OrderGroupApiRequest> request) {
        OrderGroupApiRequest body = request.getData();
       return  orderGroupRepository.findById(body.getId())
                    .map(orderGroup -> {
                        orderGroup.setStatus(body.getStatus())
                                .setOrderType(body.getOrderType())
                                .setRevAddress(body.getRevAddress())
                                .setRevName(body.getRevName())
                                .setPaymentType(body.getPaymentMethod())
                                .setTotalQuantity(body.getTotalQuantity())
                                .setTotalPrice(body.getTotalPrice())
                                .setTotalQuantity(body.getTotalQuantity())
                                .setOrderAt(body.getOrederAt())
                                .setArrivalDate(body.getArrivalDate())
                                .setUser(userRepository.getOne(body.getUserId()));
                        return orderGroup;
                    })
                    .map(changeOrderGroup-> orderGroupRepository.save(changeOrderGroup))
                    .map(newOrderGroup->response(newOrderGroup))
                    .orElseGet(()->Header.Error("NO DATA"));

    }

    @Override
    public Header delete(Long id) {
       return orderGroupRepository.findById(id)
                .map(orderGroup-> {
                    orderGroupRepository.delete(orderGroup);
                    return Header.OK();
                })
                .orElseGet(()->Header.Error("NO DATA"));
    }

    private Header<OrderGroupApiResponse> response(OrderGroup orderGroup){
        OrderGroupApiResponse body = OrderGroupApiResponse.builder()
                .id(orderGroup.getId())
                .status(orderGroup.getStatus())
                .orderType(orderGroup.getOrderType())
                .revAddress(orderGroup.getRevAddress())
                .revName(orderGroup.getRevName())
                .paymentType(orderGroup.getPaymentType())
                .totalPrice(orderGroup.getTotalPrice())
                .totalQuantity(orderGroup.getTotalQuantity())
                .orederAt(orderGroup.getOrderAt())
                .arrivalDate(orderGroup.getArrivalDate())
                .userId(orderGroup.getUser().getId())
                .build();
        return Header.OK(body);
    }

}
