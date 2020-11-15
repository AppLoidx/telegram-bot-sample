# telegram-bot-sample
telegram bot example

## Env

Set environment values to start hacking
```
BOT_TOKEN =   <bot token from telegram bot-father>
BOT_NAME  =   <your bot name>
```

### Add command
```java
import com.apploidxxx.handler.Log;
import com.apploidxxx.handler.RequestLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Message;

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
```

This command will be executed after user types `/hello`

Also, see how to work with telegram bot SDK [here](https://github.com/rubenlagus/TelegramBots)

### Add loggers

If you want logs every or specific message - you can define logger. Also, you can set execution policy.

```java
import com.apploidxxx.handler.Log;
import com.apploidxxx.handler.RequestLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Message;

@Log  // will be executed in every message
public class LogHandler implements RequestLogger {

    private static final Logger log = LoggerFactory.getLogger(LogHandler.class);

    
    @Override
    public void execute(Message message) {
        log.info("[LOG] Just log a received message : " + message.getText());
    }
}
```
```java
@Log(value = "/hello")  // will be executed only on "/hello" message. You can specify this value with another string or regex
public class HelloLogHandler implements RequestLogger {
    public static final Logger log = LoggerFactory.getLogger(HelloLogHandler.class);

    @Override
    public void execute(Message message) {
        log.info("[LOG] Received special hello command!");
    }
}

```

Execution policy set:
```java
@Log(executionTime = ExecutionTime.AFTER)
public class AfterLogHandler implements RequestLogger {

    private static final Logger log = LoggerFactory.getLogger(AfterLogHandler.class);

    @Override
    public void executeAfter(Message message, SendMessage sendMessage) {
        log.info("[LOG] Bot response >> " + sendMessage.getText());
    }
}
```

Here you need to implement executeAfter from RequestLogger. See DefaultBot sources to see implementation

There is execution timeline:
```
----> [Logger (BEFORE)] ----> [Execute command and response to user] ----> [Logger(AFTER)]
```
