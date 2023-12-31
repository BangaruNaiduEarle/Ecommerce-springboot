package com.example.demo.configuration;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        writer.println("Access Denied !! " + authException.getMessage());
    }
}

//@Component
//public class UserAuthenticationEntryPoint implements AuthenticationEntryPoint {
//	 private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
//
//	    @Override
//	    public void commence(
//	            HttpServletRequest request,
//	            HttpServletResponse response,
//	            AuthenticationException authException) throws IOException, ServletException {
//	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//	        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//	        OBJECT_MAPPER.writeValue(response.getOutputStream(), new ErrorDto("Unauthorized path"));
//	    }
//
//}
