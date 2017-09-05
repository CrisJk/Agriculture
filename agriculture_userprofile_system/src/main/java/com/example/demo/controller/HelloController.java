package com.example.demo.controller;

/**
 * Created by eason on 2017/9/4.
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello,this is a springboot demo";
    }
}