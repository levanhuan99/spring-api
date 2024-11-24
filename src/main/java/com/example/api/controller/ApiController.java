package com.example.api.controller;

import com.example.api.model.request.AuthRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/demo")
public class ApiController {
    @GetMapping("/test")
    public String test() {
        return "value";
    }

}
