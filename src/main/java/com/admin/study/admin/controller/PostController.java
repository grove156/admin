package com.admin.study.admin.controller;

import com.admin.study.admin.model.SearchParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PostController {

    @PostMapping("/postMethod")
    public void postMethod(@RequestBody SearchParam searchParam){

        //return searchParam;
    }
}
