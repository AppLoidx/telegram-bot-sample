package com.apploidxxx.handler;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * @author Arthur Kupriyanov on 15.11.2020
 */
public interface RequestHandler {
    SendMessage execute(Message message);
}
