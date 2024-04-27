package com.example.springretryissue.controller;

import com.example.springretryissue.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/")
    public void home() {
        homeService.home();
    }
}
