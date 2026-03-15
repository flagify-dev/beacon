package com.flagify.beacon;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class FlagifyRouter {
    @GetMapping("/say/{myName}")
    public String getMethodName(@PathVariable String myName) {
        return "Hello, " + myName + "!";
    }
    
}
