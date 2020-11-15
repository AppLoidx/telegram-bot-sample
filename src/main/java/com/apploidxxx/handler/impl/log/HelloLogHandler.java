package com.apploidxxx.handler.impl.log;

import com.apploidxxx.handler.Log;
import com.apploidxxx.handler.RequestLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * This logger will be executed only when called command /hello
 *
 * @author Arthur Kupriyanov on 15.11.2020
 */
@Log(value = "/hello")
public class HelloLogHandler implements RequestLogger {
    public static final Logger log = LoggerFactory.getLogger(HelloLogHandler.class);

    @Override
    public void execute(Message message) {
        log.info("[LOG] Received special hello command!");
    }
}
