package com.apploidxxx.bot;

import com.apploidxxx.commander.CommandService;
import com.apploidxxx.handler.ExecutionTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;


/**
 * @author Arthur Kupriyanov on 15.11.2020
 */
public class DefaultBot extends TelegramLongPollingBot {
    private static final Logger log = LoggerFactory.getLogger(DefaultBot.class);

    public DefaultBot() {
        CommandService.init();
        log.info("Bot initialized!");
    }

    @Override
    public String getBotUsername() {
        return System.getenv("BOT_NAME");
    }

    @Override
    public String getBotToken() {
        return System.getenv("BOT_TOKEN");
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            Message message = update.getMessage();
            if (message != null && message.hasText()) {
                // run "before" loggers
                CommandService.findLoggers(message.getText(), ExecutionTime.BEFORE)
                        .forEach(logger -> logger.execute(message));

                // command execution
                SendMessage response;
                this.execute(response = CommandService.serve(message.getText()).execute(message));

                // run "after" loggers
                CommandService.findLoggers(message.getText(), ExecutionTime.AFTER)
                        .forEach(logger -> logger.executeAfter(message, response));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
