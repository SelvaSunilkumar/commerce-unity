package com.github.SelvaSunilkumar.Commerce.Unity.correlation;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class CorrelationIdInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        String correlationId = CorrelationIdHolder.getCorrelationId();

        if (correlationId != null) {
            request.getHeaders().add(CorrelationIdConstants.HEADER_NAME, correlationId);
        }

        return execution.execute(request, body);

    }
}
