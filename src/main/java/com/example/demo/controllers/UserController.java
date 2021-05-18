package com.example.demo.controllers;

import com.example.demo.model.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDao userDao;

//    @GetMapping("")
//    public String index(Model model) {
//        return null;
//    }
//    @GetMapping("/{id}")
//    public String show(@PathVariable("id") int id,Model model) {
//        return null;
//    }
}
