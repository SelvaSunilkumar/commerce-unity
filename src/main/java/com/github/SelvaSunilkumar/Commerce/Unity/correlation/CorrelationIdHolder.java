package com.github.SelvaSunilkumar.Commerce.Unity.correlation;

public class CorrelationIdHolder {
    private static final ThreadLocal<String> correlationId = new ThreadLocal<>();

    public static void setCorrelationId(String correlationId) {
        CorrelationIdHolder.correlationId.set(correlationId);
    }

    public static String getCorrelationId() {
        return correlationId.get();
    }

    public static void clearCorrelationId() {
        correlationId.remove();
    }
}
