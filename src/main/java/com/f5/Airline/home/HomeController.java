package com.f5.Airline.home;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
@RequestMapping("${api-endpoint}")
public class HomeController {

    @GetMapping("")
    public String index() {
        return "Hello from server!";
    }

    @GetMapping("/public")
    public String gotToPublic() {
        return "Public path";
    }

    @GetMapping("/private")
    public String goToPrivate() {
        return "Private path";
    }

}
