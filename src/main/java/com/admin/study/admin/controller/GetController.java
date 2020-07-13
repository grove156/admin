package com.admin.study.admin.controller;

import com.admin.study.admin.model.SearchParam;
import com.admin.study.admin.model.network.Header;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // localhost:8080/api
public class GetController {

    @GetMapping(path = "/getMethod")
    public String getRequest(){
        return "Hi get method";
    }

    @GetMapping("/getParam/{id}/{pw}")
    public String getParam(@PathVariable String id, @PathVariable String pw){
        System.out.println("id: " + id);
        System.out.println("password: " + pw);

        return id + pw;
    }

    //localhost:8080/getMultiParam?account=1234&eamil=1234@abc.com&page=10
    //localhost:8080/getMultiParam/123/grove156@gmail.com/15
    @GetMapping("/getMultiParam")
    public SearchParam getMultiParam (@RequestParam SearchParam searchParam){
        return searchParam;
    }

    @GetMapping("/header")
    public Header getHeader(){

        //{"resultCode" : "OK", "description" : "OK"}
        return Header.builder().resultCode("OK").description("OK").build();
    }

}