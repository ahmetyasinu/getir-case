package com.getir.readingisgood.authentication.config;

public class SecurityConstants {
    public static final long JWT_TOKEN_VALIDITY = 10 * 60 * 60;
    public static final String USER = "getir";
    public static final String PASSWORD = "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6";
    public static final String HEADER_STRING = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String APP_NAME = "Reading is Good";
    public static final String CONTACT_NAME = "Nazlı Ece Çavlan";
    public static final String DESCRIPTION = "Reading is Good API Documentation";
    public static final String LICENCE = "Apache 2.0";

    public static final String[] AUTHENTICATION_WHITELIST = {
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/configuration/**",
            "/webjars/**",
            "/h2-console/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html/**"
    };
}
