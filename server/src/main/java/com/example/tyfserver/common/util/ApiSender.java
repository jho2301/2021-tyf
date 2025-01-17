package com.example.tyfserver.common.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

public class ApiSender {

    private static final RestTemplate REST_TEMPLATE = new RestTemplate();

    static { // todo: 한글대신 유니코드 나오는 현상
        REST_TEMPLATE.getMessageConverters()
                .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
    }

    private ApiSender() {
    }

    public static <T> String send(String url, HttpMethod method, HttpEntity<T> entity) {
        return send(url, method, entity, String.class);
    }

    public static <T, U> T send(String url, HttpMethod method, HttpEntity<U> entity, Class<T> returnType) {
        return REST_TEMPLATE.exchange(url, method, entity, returnType).getBody();
    }
}
