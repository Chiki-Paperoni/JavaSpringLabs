package com.example.demo.controllers;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/greetings")
public class FirstController {
    @GetMapping("")
    public @ResponseBody String hello() {
        return "hello";
    }

}
