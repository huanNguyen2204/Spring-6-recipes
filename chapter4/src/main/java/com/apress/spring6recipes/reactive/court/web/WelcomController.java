package com.apress.spring6recipes.reactive.court.web;

import com.apress.spring6recipes.reactive.court.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/welcome")
public class WelcomController {

    @GetMapping
    public Mono<String> welcome(@RequestParam(defaultValue = "world") String name) {
        return Mono.just("Hello " + name + " from Spring WebFlux");
    }
}
