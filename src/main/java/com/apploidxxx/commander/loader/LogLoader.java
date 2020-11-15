package com.apploidxxx.commander.loader;

import com.apploidxxx.handler.Log;
import com.apploidxxx.handler.RequestLogger;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Arthur Kupriyanov on 15.11.2020
 */
public final class LogLoader {
    private final static Logger log = LoggerFactory.getLogger(LogLoader.class);
    private final static ObjectCreator OBJECT_CREATOR = new ObjectCreator();
    private final static Reflections reflections = new Reflections("com.apploidxxx");

    private LogLoader() {
    }

    public static Map<String, Set<RequestLogger>> loadLoggers() {
        Set<Class<?>> annotatedLoggers = reflections.getTypesAnnotatedWith(Log.class);

        final Map<String, Set<RequestLogger>> commandsMap = new HashMap<>();
        final Class<RequestLogger> requiredInterface = RequestLogger.class;

        for (Class<?> clazz : annotatedLoggers) {
            if (LoaderUtils.isImplementedInterface(clazz, requiredInterface)) {
                for (Constructor<?> c : clazz.getDeclaredConstructors()) {
                    //noinspection unchecked
                    Constructor<RequestLogger> castedConstructor = (Constructor<RequestLogger>) c;
                    String name = extractCommandName(clazz);
                    commandsMap.computeIfAbsent(name, n -> new HashSet<>());
                    commandsMap.get(extractCommandName(clazz)).add(OBJECT_CREATOR.instantiateClass(castedConstructor));

                }

            } else {
                log.warn("Command didn't implemented: " + requiredInterface.getCanonicalName());
            }
        }

        return commandsMap;
    }

    private static String extractCommandName(Class<?> clazz) {
        Log handler = clazz.getAnnotation(Log.class);
        if (handler == null) {
            throw new IllegalArgumentException("Passed class without " + Log.class.getName() + " annotation");
        } else {
            return handler.value();
        }
    }


}
