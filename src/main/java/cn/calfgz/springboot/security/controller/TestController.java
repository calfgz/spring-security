package cn.calfgz.springboot.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author calfgz
 * @description:
 * @date 2020-03-24 11:42
 */
@RestController
public class TestController {

    @GetMapping("/index")
    public Object index(Authentication authentication) {
        return authentication;
    }

    @GetMapping("/test")
    public String test() {
        return "hello spring security.";
    }
}
