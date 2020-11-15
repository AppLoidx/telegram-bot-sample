package com.apploidxxx.commander;

import com.apploidxxx.commander.loader.CommandLoader;
import com.apploidxxx.commander.loader.LogLoader;
import com.apploidxxx.handler.ExecutionTime;
import com.apploidxxx.handler.Log;
import com.apploidxxx.handler.RequestHandler;
import com.apploidxxx.handler.RequestLogger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Arthur Kupriyanov on 15.11.2020
 */
public final class CommandService {

    private static final Map<String, RequestHandler> commandsMap = new HashMap<>();
    private static final Map<String, Set<RequestLogger>> loggersMap = new HashMap<>();

    private CommandService() {
    }

    public static synchronized void init() {
        initCommands();
        initLoggers();
    }

    private static void initCommands() {
        commandsMap.putAll(CommandLoader.readCommands());
    }

    private static void initLoggers() {
        loggersMap.putAll(LogLoader.loadLoggers());
    }

    public static RequestHandler serve(String message) {
        for (Map.Entry<String, RequestHandler> entry : commandsMap.entrySet()) {
            if (entry.getKey().equals(message)) {
                return entry.getValue();
            }
        }

        return msg -> SendMessage.builder()
                .text("Команда не найдена")
                .chatId(String.valueOf(msg.getChatId()))
                .build();
    }

    public static Set<RequestLogger> findLoggers(String message, ExecutionTime executionTime) {
        final Set<RequestLogger> matchedLoggers = new HashSet<>();
        for (Map.Entry<String, Set<RequestLogger>> entry : loggersMap.entrySet()) {
            for (RequestLogger logger : entry.getValue()) {
                if (logger.getClass().getAnnotation(Log.class).executionTime() == executionTime)
                    if (message.matches(entry.getKey())) {
                        matchedLoggers.add(logger);
                    }
            }

        }

        return matchedLoggers;
    }


}
