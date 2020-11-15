package com.apploidxxx.handler;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Arthur Kupriyanov on 14.11.2020
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Handler {
    String value();
}
