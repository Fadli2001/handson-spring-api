package com.enigma.simplebackend.controller;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/hello")
@RestController
public class HelloController {
    @GetMapping
    public String sayHello(){
        return "Hello World!";
    }

}
