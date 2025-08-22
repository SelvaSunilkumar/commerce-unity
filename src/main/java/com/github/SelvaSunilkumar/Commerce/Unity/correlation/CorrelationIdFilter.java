package com.github.SelvaSunilkumar.Commerce.Unity.correlation;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class CorrelationIdFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String correlationId = request.getHeader(CorrelationIdConstants.HEADER_NAME);

        if (correlationId == null || correlationId.isEmpty()) {
            correlationId = UUID.randomUUID().toString();
        }

        CorrelationIdHolder.setCorrelationId(correlationId);
        request.setAttribute(CorrelationIdConstants.HEADER_NAME, correlationId);
        MDC.put(CorrelationIdConstants.HEADER_NAME, correlationId);
        logger.info(String.format("setting correlation Id: %s", correlationId));

        try {
            filterChain.doFilter(request, servletResponse);
        } finally {
            CorrelationIdHolder.clearCorrelationId();
            MDC.remove(CorrelationIdConstants.HEADER_NAME);
        }
    }
}
