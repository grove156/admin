package com.admin.study.admin.service;

import com.admin.study.admin.ifs.CrudInterface;
import com.admin.study.admin.model.entity.User;
import com.admin.study.admin.model.enumclass.UserStatus;
import com.admin.study.admin.model.network.Header;
import com.admin.study.admin.model.network.request.UserApiRequest;
import com.admin.study.admin.model.network.response.UserApiResponse;
import com.admin.study.admin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserRepository userRepository;

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

        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        //id -> repository getOne, getById
        Optional<User> optional =userRepository.findById(id);

        return optional
                .map(user -> response(user))
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

    private Header<UserApiResponse> response(User user){
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

        return Header.OK(userApiResponse);
    }
}
