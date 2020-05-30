package com.example.demo.social;

import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthenticationMethod;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;


public enum AppOAuth2Provider {

    TWITCH {
        public ClientRegistration getClientRegistration() {
            ClientRegistration.Builder builder = ClientRegistration.withRegistrationId("twitch");
            builder.clientName("Twitch");
            builder.clientId("q14d7nph72xv6h8o5lftg9q24gqu1o");
            builder.clientSecret("sdfbmxfem56bfeuc623geonj89s9hu");
            builder.scope("user:read:email+channel:read:subscriptions");
            builder.authorizationUri("https://id.twitch.tv/oauth2/authorize");
            builder.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE);
            builder.tokenUri("https://id.twitch.tv/oauth2/token");
            builder.clientAuthenticationMethod(ClientAuthenticationMethod.POST);
//            builder.userInfoUri("https://api.twitch.tv/helix/users");
            builder.userInfoUri("https://id.twitch.tv/oauth2/userinfo");
            builder.userInfoAuthenticationMethod(AuthenticationMethod.HEADER);
            builder.redirectUriTemplate("{baseUrl}/oauth2/code/{registrationId}");
            builder.userNameAttributeName("sub");
            return builder.build();
        }
    };
/*            builder.scope(new String[]{"user:read:email+channel:read:subscriptions"});

*           twitch:
            clientId: "q14d7nph72xv6h8o5lftg9q24gqu1o"
            clientSecret: "sdfbmxfem56bfeuc623geonj89s9hu"
            clientName: "Twitch"
            authorizationGrantType: "authorization_code"
            clientAuthenticationMethod: "post"
#            redirectUri: "https://beta.leagueofkoher.com/"
            redirectUri: "http://localhost:9090/oauth/twitch/callback"
#            redirectUri: "/redirected"
            scope: "user:read:email+channel:read:subscriptions"
        provider:
          twitch:
            authorizationUri: "https://id.twitch.tv/oauth2/authorize"
            tokenUri: "https://id.twitch.tv/oauth2/token"
            userInfoUri: "https://api.twitch.tv/kraken/user"
            userInfoAuthenticationMethod: "get"*/


    private AppOAuth2Provider() {
    }

    public abstract ClientRegistration getClientRegistration();
}
