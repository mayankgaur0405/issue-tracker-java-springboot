package com.issuetracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

@Controller
public class WebController {
    
    @GetMapping("/")
    public String home() {
        return "index";
    }
    
    @GetMapping("/health")
    @ResponseBody
    public ResponseEntity<Map<String, String>> health() {
        Map<String, String> status = new HashMap<>();
        status.put("status", "UP");
        status.put("service", "Issue Tracker");
        return ResponseEntity.ok(status);
    }
}


