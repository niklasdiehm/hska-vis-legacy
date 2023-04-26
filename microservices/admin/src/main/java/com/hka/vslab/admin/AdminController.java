package com.hka.vslab.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    @GetMapping("/test")
    public void test() {
        System.out.println("test");
    }
}
