package com.bank.profile.audit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks service methods whose execution should be recorded in the audit log.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Auditable {

    /**
     * Logical name of the entity that is affected by the operation.
     */
    String entity();

    /**
     * Type of operation that has to be logged.
     */
    OperationType operation();
}
