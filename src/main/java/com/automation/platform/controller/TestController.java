package com.automation.platform.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${test.hello}")
    public String hello;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!\n"+ hello;
    }

    @PostMapping("/hello/post")
    public String helloPost (String name){
        return "Hello World! Post" + name;
    }
}
