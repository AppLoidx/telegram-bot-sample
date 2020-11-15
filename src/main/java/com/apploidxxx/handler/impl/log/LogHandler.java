package com.apploidxxx.handler.impl.log;

import com.apploidxxx.handler.Log;
import com.apploidxxx.handler.RequestLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * @author Arthur Kupriyanov on 15.11.2020
 */
@Log
public class LogHandler implements RequestLogger {

    private static final Logger log = LoggerFactory.getLogger(LogHandler.class);


    @Override
    public void execute(Message message) {
        log.info("[LOG] Just log a received message : " + message.getText());
    }
}
