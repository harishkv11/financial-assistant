package com.harishverma.financial.assistant.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/ping")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("pong", HttpStatus.OK);
    }
}
