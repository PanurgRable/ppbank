package com.bank.profile.audit;

/**
 * Stores audit-related contextual information for the current thread.
 */
public final class AuditContextHolder {

    private static final ThreadLocal<Object> PREVIOUS_STATE = new ThreadLocal<>();

    private AuditContextHolder() {
    }

    public static void setPreviousState(Object value) {
        PREVIOUS_STATE.set(value);
    }

    public static Object getPreviousState() {
        return PREVIOUS_STATE.get();
    }

    public static void clear() {
        PREVIOUS_STATE.remove();
    }
}
