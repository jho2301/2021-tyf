package com.example.tyfserver.auth;

import com.example.tyfserver.AcceptanceTest;
import com.example.tyfserver.auth.dto.SignUpResponse;
import com.example.tyfserver.auth.dto.TokenResponse;
import com.example.tyfserver.auth.exception.SignUpRequestException;
import com.example.tyfserver.common.dto.ErrorResponse;
import com.example.tyfserver.member.dto.SignUpRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthAcceptanceTest extends AcceptanceTest {

    public static ExtractableResponse<Response> 로그인_요청() {
        return paramGet("/oauth2/login/KAKAO", "code", "code").extract();
    }

    public static ExtractableResponse<Response> 회원생성_준비를_요청() {
        return paramGet("/oauth2/ready/KAKAO", "code", "code").extract();
    }

    public static ExtractableResponse<Response> 회원생성을_요청(String email, String oauthType, String nickname, String pageName) {
        return post("/oauth2/signup", new SignUpRequest(email, oauthType, nickname, pageName)).extract();
    }

    public static SignUpResponse 회원가입_후_로그인되어_있음(String email, String oauthType, String nickname, String pageName) {
        return 회원생성을_요청(email, oauthType, nickname, pageName)
                .as(SignUpResponse.class);
    }

    public static TokenResponse 로그인되어_있음() {
        return 로그인_요청().as(TokenResponse.class);
    }

    @Test
    @DisplayName("성공적인 회원 가입을 하는 경우")
    public void signUp() {
        SignUpResponse signUpResponse = 회원생성을_요청("tyf@gmail.com", "GOOGLE", "myNickname", "mypagename").as(SignUpResponse.class);

        assertThat(signUpResponse.getPageName()).isEqualTo("mypagename");
        assertThat(signUpResponse.getToken()).isNotNull();
    }

    @Test
    @DisplayName("유효하지 않은 Request로 회원 가입을 하는 경우")
    public void signUpInvalidRequestFailed() {
        ErrorResponse errorResponse = 회원생성을_요청("tyf@gmail.com", "GOOGLE", "myNickname", "PPP").as(ErrorResponse.class);

        assertThat(errorResponse.getErrorCode()).isEqualTo(SignUpRequestException.ERROR_CODE);
    }

    @Test
    @DisplayName("로그인에 성공함")
    public void login() {
        회원생성을_요청(DEFAULT_EMAIL, "KAKAO", "nickname", "pagename");
        TokenResponse tokenResponse = 로그인_요청().as(TokenResponse.class);

        assertThat(tokenResponse.getToken()).isNotNull();
    }

}
