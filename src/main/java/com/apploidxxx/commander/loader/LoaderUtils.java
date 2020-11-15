package com.apploidxxx.commander.loader;

/**
 * @author Arthur Kupriyanov on 15.11.2020
 */
public final class LoaderUtils {
    private LoaderUtils() {
    }

    public static boolean isImplementedInterface(Class<?> clazz, Class<?> interfaceClazz) {
        boolean implemented = false;
        for (Class<?> i : clazz.getInterfaces()) {
            if (i == interfaceClazz) {
                implemented = true;
                break;
            }
        }
        return implemented;
    }
}
