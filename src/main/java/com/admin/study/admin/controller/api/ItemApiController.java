package com.admin.study.admin.controller.api;

import com.admin.study.admin.controller.CrudController;
import com.admin.study.admin.ifs.CrudInterface;
import com.admin.study.admin.model.network.Header;
import com.admin.study.admin.model.network.request.ItemApiRequest;
import com.admin.study.admin.model.network.response.ItemApiResponse;
import com.admin.study.admin.service.ItemApiLogicService;
import jdk.internal.org.jline.utils.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@Slf4j
@RestController
@RequestMapping("/api/item")
public class ItemApiController extends CrudController<ItemApiRequest, ItemApiResponse> {
    @Autowired
    private ItemApiLogicService itemApiLogicService;

    //추상화한 CrudController의 baseSerivce를 itemApiLogicService와 연결시킴
    @PostConstruct
    public void init(){
        this.baseService = itemApiLogicService;
    }

    //추상화한  CrudController를 상속받아 crud기능을 상속받음
}
