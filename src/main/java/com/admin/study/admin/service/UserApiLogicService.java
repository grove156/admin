package com.admin.study.admin.service;

import com.admin.study.admin.ifs.CrudInterface;
import com.admin.study.admin.model.entity.Item;
import com.admin.study.admin.model.entity.OrderGroup;
import com.admin.study.admin.model.entity.User;
import com.admin.study.admin.model.enumclass.UserStatus;
import com.admin.study.admin.model.network.Header;
import com.admin.study.admin.model.network.Pagination;
import com.admin.study.admin.model.network.request.UserApiRequest;
import com.admin.study.admin.model.network.response.ItemApiResponse;
import com.admin.study.admin.model.network.response.OrderGroupApiResponse;
import com.admin.study.admin.model.network.response.UserApiResponse;
import com.admin.study.admin.model.network.response.UserOrderInfoResponse;
import com.admin.study.admin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderGroupApiLogicService orderGroupApiLogicService;

    @Autowired
    private ItemApiLogicService itemApiLogicService;

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {
        //1.request data
        UserApiRequest userApiRequest = request.getData();

        //2.user create
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status(UserStatus.REGISTERED)
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();

        User newUser = userRepository.save(user);

        //3. UserApiResponse

        return Header.OK(response(newUser));
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        //id -> repository getOne, getById
        Optional<User> optional =userRepository.findById(id);

        return optional
                .map(user -> response(user))
                .map(userApiResponse -> Header.OK(userApiResponse))
                .orElseGet(() -> Header.Error("NO DATA"));
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        //1. data
        UserApiRequest userApiRequest = request.getData();
        //2. id -> search data
        Optional<User> optional = userRepository.findById(userApiRequest.getId());

        optional.map(user -> {
        //3. UPDATE
            user.setAccount(userApiRequest.getAccount())
                    .setPassword(userApiRequest.getPassword())
                    .setStatus(userApiRequest.getStatus())
                    .setPhoneNumber(userApiRequest.getPhoneNumber())
                    .setEmail(userApiRequest.getEmail())
                    .setRegisteredAt(userApiRequest.getRegisteredAt())
                    .setUnregisteredAt(userApiRequest.getUnregisteredAt());
            return user;
        //4. userApiResponse

        })
                .map(user -> userRepository.save(user))
                .map(user -> response(user))
                .map(userApiResponse -> Header.OK(userApiResponse))
                .orElseGet(()->Header.Error("NO DATA"));
        return null;
    }

    @Override
    public Header delete(Long id) {
        //id -> repository -> findById
        Optional<User> optional = userRepository.findById(id);

        //repository -> delete
        return optional.map(user->{
            userRepository.delete(user);
            return Header.OK();
        }).orElseGet(()->Header.Error("NO DATA"));
        //response return
    }

    private UserApiResponse response(User user){
        //user -> userApiResponse

        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        return userApiResponse;
    }

    public Header<List<UserApiResponse>> search(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);

        List<UserApiResponse> userApiResponseList = users.stream()
                .map(user -> response(user))
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(users.getTotalPages())
                .totalElements(users.getTotalElements())
                .currentPage(users.getNumber())
                .currentElements(users.getNumberOfElements())
                .build();

        //List<userApiResponse> - > Header<List<UserApiResponse>>
        return Header.OK(userApiResponseList,pagination);

    }

    public Header<UserOrderInfoResponse> orderInfo(Long id) {
        //user
        User user = userRepository.getOne(id);
        UserApiResponse userApiResponse = response(user);

        //orderGroup
        List<OrderGroup> orderGroupList = user.getOrderGroupList();
        List<OrderGroupApiResponse> orderGroupApiResponseList = orderGroupList.stream()
                .map(orderGroup -> {
                    OrderGroupApiResponse orderGroupApiResponse = orderGroupApiLogicService.response(orderGroup).getData();

                    //item api response
                    List<ItemApiResponse> itemApiResponseList = orderGroup.getOrderDetailList().stream()
                            .map(detail -> detail.getItem())
                            .map(item -> itemApiLogicService.response(item).getData())
                            .collect(Collectors.toList());
                    orderGroupApiResponse.setItemApiResponseList(itemApiResponseList);
                    return orderGroupApiResponse;
                })
                .collect(Collectors.toList());

        userApiResponse.setOrderGroupApiResponsesList(orderGroupApiResponseList);

        UserOrderInfoResponse userOrderInfoResponse = UserOrderInfoResponse.builder()
                .userApiResponse(userApiResponse)
                .build();

        return Header.OK(userOrderInfoResponse);
        //item


    }
}
