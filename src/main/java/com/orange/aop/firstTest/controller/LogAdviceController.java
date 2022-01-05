package com.orange.aop.firstTest.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aop")
public class LogAdviceController {

    @GetMapping("/get")
    public JSONObject get(){
        return JSON.parseObject("{\"message\":\"SUCCESS\",\"code\":200}");
    }

    @PostMapping("/post")
    public JSONObject getTest(){
        return JSON.parseObject("{\"message\":\"SUCCESS\",\"code\":200}");
    }

}
