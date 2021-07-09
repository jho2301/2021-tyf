package com.example.tyfserver.auth.config;

import com.example.tyfserver.auth.domain.GoogleOAuth2;
import com.example.tyfserver.auth.domain.KakaoOAuth2;
import com.example.tyfserver.auth.domain.NaverOAuth2;
import com.example.tyfserver.auth.domain.Oauth2Type;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@RequiredArgsConstructor
public class OAuth2TypeDependencyInjector {

    private final GoogleOAuth2 googleOAuth2;
    private final KakaoOAuth2 kakaoOAuth2;
    private final NaverOAuth2 naverOAuth2;

    @PostConstruct
    public void inject() {
        for (Oauth2Type value : Oauth2Type.values()) {
            if (value.equals(Oauth2Type.GOOGLE)) {
                value.setoAuth2TypeInterface(googleOAuth2);
            }
            if (value.equals(Oauth2Type.NAVER)) {
                value.setoAuth2TypeInterface(naverOAuth2);
            }
            if (value.equals(Oauth2Type.KAKAO)) {
                value.setoAuth2TypeInterface(kakaoOAuth2);
            }
        }
    }
}
