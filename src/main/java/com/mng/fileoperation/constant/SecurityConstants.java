package com.mng.fileoperation.constant;

public class SecurityConstants {

    public static final String SIGN_UP_URL = "/users/sign-up";
    public static final String SWAGGER_URL = "/v2/api-docs";
    public static final String SWAGGER_URL_2 = "/fileoperation/api/swagger-ui/";
    public static final String SWAGGER_URL_3 = "/swagger-ui.html";
    public static final String SECRET = "MySecretKey";
    public static final long EXPIRATION_TIME = 432_000_000; // 5 gün
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    public static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**"
            // other public endpoints of your API may be appended to this array
    };
}

