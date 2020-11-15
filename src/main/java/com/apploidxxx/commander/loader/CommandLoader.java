package com.apploidxxx.commander.loader;

import com.apploidxxx.handler.Handler;
import com.apploidxxx.handler.RequestHandler;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Arthur Kupriyanov on 15.11.2020
 */
public final class CommandLoader {

    private final static Logger log = LoggerFactory.getLogger(CommandLoader.class);
    private final static ObjectCreator OBJECT_CREATOR = new ObjectCreator();


    private CommandLoader() {
    }

    private final static Reflections reflections = new Reflections("com.apploidxxx");

    public static Map<String, RequestHandler> readCommands() {
        Set<Class<?>> annotatedCommands = reflections.getTypesAnnotatedWith(Handler.class);

        final Map<String, RequestHandler> commandsMap = new HashMap<>();

        final Class<RequestHandler> requiredInterface = RequestHandler.class;

        for (Class<?> clazz : annotatedCommands) {
            if (LoaderUtils.isImplementedInterface(clazz, requiredInterface)) {
                for (Constructor<?> c : clazz.getDeclaredConstructors()) {
                    //noinspection unchecked
                    Constructor<RequestHandler> castedConstructor = (Constructor<RequestHandler>) c;
                    commandsMap.put(extractCommandName(clazz), OBJECT_CREATOR.instantiateClass(castedConstructor));
                }


            } else {
                log.warn("Command didn't implemented: " + requiredInterface.getCanonicalName());

            }
        }

        return commandsMap;
    }

    private static String extractCommandName(Class<?> clazz) {
        Handler handler = clazz.getAnnotation(Handler.class);
        if (handler == null) {
            throw new IllegalArgumentException("Passed class without Handler annotation");
        } else {
            return handler.value();
        }
    }


}
