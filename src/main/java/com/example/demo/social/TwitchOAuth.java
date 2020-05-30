package com.example.demo.social;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@RestController
public class TwitchOAuth {

    @GetMapping(value = "/")
    public String home(@AuthenticationPrincipal OAuth2User
                                   principal) {
        return "<h1> Hello,"+principal.getName()+" {} <h1>";
    }


    @PostMapping("/oauth/twitch/callback")
    public Map<String, String> fgh(@RequestBody Map<String, String> values) {
        System.out.println("======================================================");
        System.out.println("[POST] VALUES FROM OAUTH2 TWITCH:");
        System.out.println(values.toString());
        return values;
    }


    @GetMapping(value = "/oauth2/code/twitch")
    public Map<String, String> test(@RequestParam Map<String, String> params) {
        return params;
//        return params;
//        return "<h1>Hello User<h1>";
    }

    @GetMapping(value = "/oau")
    public OAuth2User principal(OAuth2User oa2u) {
        return oa2u;
    }
}
