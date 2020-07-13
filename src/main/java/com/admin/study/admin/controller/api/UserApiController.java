package com.admin.study.admin.controller.api;

import com.admin.study.admin.ifs.CrudInterface;
import com.admin.study.admin.model.network.Header;
import com.admin.study.admin.model.network.request.UserApiRequest;
import com.admin.study.admin.model.network.response.UserApiResponse;
import com.admin.study.admin.service.UserApiLogicService;
import jdk.internal.org.jline.utils.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserApiLogicService userApiLogicService;

    @Override
    @PostMapping
    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {
        Log.info("{}", request);
        return userApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<UserApiResponse> read(@PathVariable Long id){
        Log.info("read id: {}",id);
        userApiLogicService.read(id);

        return null;
    }

    @Override
    @PutMapping("")
    public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> userApiRequest) {
        return null;
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        Log.info("delete:{}",id);
        return userApiLogicService.delete(id);
    }

}
