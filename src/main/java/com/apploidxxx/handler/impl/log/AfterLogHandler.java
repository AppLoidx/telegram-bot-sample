package com.apploidxxx.handler.impl.log;

import com.apploidxxx.handler.ExecutionTime;
import com.apploidxxx.handler.Log;
import com.apploidxxx.handler.RequestLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * @author Arthur Kupriyanov on 15.11.2020
 */
@Log(executionTime = ExecutionTime.AFTER)
public class AfterLogHandler implements RequestLogger {

    private static final Logger log = LoggerFactory.getLogger(AfterLogHandler.class);

    @Override
    public void executeAfter(Message message, SendMessage sendMessage) {
        log.info("[LOG] Bot response >> " + sendMessage.getText());
    }
}
