package com.apploidxxx.handler.impl;

import com.apploidxxx.handler.Handler;
import com.apploidxxx.handler.RequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;


/**
 * @author Arthur Kupriyanov on 15.11.2020
 */
@Handler("/hello")
public class HelloHandler implements RequestHandler {

    private static final Logger log = LoggerFactory.getLogger(HelloHandler.class);

    @Override
    public SendMessage execute(Message message) {
        log.info("Executing message from : " + message.getText());
        return SendMessage.builder()
                .text("Yaks")
                .chatId(String.valueOf(message.getChatId()))
                .build();
    }
}
