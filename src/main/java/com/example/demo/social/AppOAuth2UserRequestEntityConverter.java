package com.example.demo.social;

import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.AuthenticationMethod;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

public class AppOAuth2UserRequestEntityConverter implements Converter<OAuth2UserRequest, RequestEntity<?>> {
    private static final MediaType DEFAULT_CONTENT_TYPE = MediaType.valueOf(APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
    @Override
    public RequestEntity<?> convert(OAuth2UserRequest userRequest) {
        ClientRegistration clientRegistration = userRequest.getClientRegistration();

        HttpMethod httpMethod = HttpMethod.GET;
        if (AuthenticationMethod.FORM.equals(clientRegistration.getProviderDetails().getUserInfoEndpoint().getAuthenticationMethod())) {
            httpMethod = HttpMethod.POST;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Client-ID", userRequest.getClientRegistration().getClientId());
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        URI uri = UriComponentsBuilder.fromUriString(clientRegistration.getProviderDetails().getUserInfoEndpoint().getUri())
                .build()
                .toUri();

        RequestEntity<?> request;
        if (HttpMethod.POST.equals(httpMethod)) {
            headers.setContentType(DEFAULT_CONTENT_TYPE);
            MultiValueMap<String, String> formParameters = new LinkedMultiValueMap<>();
            formParameters.add(OAuth2ParameterNames.ACCESS_TOKEN, userRequest.getAccessToken().getTokenValue());
            request = new RequestEntity<>(formParameters, headers, httpMethod, uri);
        } else {
            headers.setBearerAuth(userRequest.getAccessToken().getTokenValue());

            request = new RequestEntity<>(headers, httpMethod, uri);
        }
        return request;
    }
}
