package com.github.SelvaSunilkumar.Commerce.Unity.correlation;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class CorrelationIdFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String correlationId = request.getHeader(CorrelationIdConstants.HEADER_NAME);

        if (correlationId == null || correlationId.isEmpty()) {
            correlationId = UUID.randomUUID().toString();
        }

        CorrelationIdHolder.setCorrelationId(correlationId);
        MDC.put(CorrelationIdConstants.HEADER_NAME, correlationId);

        try {
            filterChain.doFilter(request, servletResponse);
        } finally {
            CorrelationIdHolder.clearCorrelationId();
            MDC.remove(CorrelationIdConstants.HEADER_NAME);
        }
    }
}
