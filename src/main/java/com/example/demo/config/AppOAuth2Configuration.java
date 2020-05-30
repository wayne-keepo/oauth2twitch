package com.example.demo.config;

import com.example.demo.social.AppOAuth2Provider;
import com.example.demo.social.AppOAuth2UserRequestEntityConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.client.web.AuthenticatedPrincipalOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Configuration
//@EnableWebSecurity
public class AppOAuth2Configuration extends WebSecurityConfigurerAdapter {

    //    @EnableWebSecurity
//    public static class OAuth2LoginSecurityConfig extends WebSecurityConfigurerAdapter {
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http
//                    .authorizeRequests(authorize -> authorize
//                            .anyRequest().authenticated()
//                    )
//                    .oauth2Login(withDefaults());
//        }
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorize -> authorize
                        .anyRequest().authenticated()
                )
                .oauth2Login(
                        oauth2 -> oauth2
                                .redirectionEndpoint(redirection -> redirection
                                        .baseUri("/oauth2/code/*")

                                )
//                                .userInfoEndpoint(
//                                        userInfo -> userInfo.userService(this.oauth2UserService())
//                                )
                ).oauth2Client();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(AppOAuth2Provider.TWITCH.getClientRegistration());
    }

    @Bean
    public OAuth2AuthorizedClientService authorizedClientService(
            ClientRegistrationRepository clientRegistrationRepository) {
        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository);
    }

    @Bean
    public OAuth2AuthorizedClientRepository authorizedClientRepository(
            OAuth2AuthorizedClientService authorizedClientService) {
        return new AuthenticatedPrincipalOAuth2AuthorizedClientRepository(authorizedClientService);
    }

//    @Bean
//    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2UserService() {
//        DefaultOAuth2UserService defaultOAuth2UserService = new DefaultOAuth2UserService();
//        defaultOAuth2UserService.setRequestEntityConverter(new AppOAuth2UserRequestEntityConverter());
//        return defaultOAuth2UserService;
//    }
}
