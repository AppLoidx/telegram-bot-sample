package com.apploidxxx.handler.impl.log;

import com.apploidxxx.handler.ExecutionTime;
import com.apploidxxx.handler.Log;
import com.apploidxxx.handler.RequestLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * @author Arthur Kupriyanov on 16.11.2020
 */
@Log(executionTime = {ExecutionTime.AFTER, ExecutionTime.BEFORE})
public class AfterAndBeforeLogger implements RequestLogger {
    private static final Logger log = LoggerFactory.getLogger(AfterAndBeforeLogger.class);

    @Override
    public void execute(Message message) {
        log.info("Before execute");
    }

    @Override
    public void executeAfter(Message message, SendMessage sendMessage) {
        log.info("After execute");
    }
}
