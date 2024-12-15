package com.example.GoShare.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomeAccessDeniedHandler implements AccessDeniedHandler {

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    /**
     * This method is called when a user attempts to access a resource they do not
     * have permission to access.
     * Instead of directly handling the exception, we delegate it to the
     * HandlerExceptionResolver for handling.
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {

        // Delegate the exception handling to Spring MVC global exception
        resolver.resolveException(request, response, null, accessDeniedException);
    }

}
