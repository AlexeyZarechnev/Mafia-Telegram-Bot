package alexey_zarechnev.service;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import alexey_zarechnev.config.BotConfig;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig config;

    public TelegramBot(BotConfig config) {
        super(config.getAPIkey());
        for (int i = 0; i < 100000000; ++i) {
            System.err.println("I am pidor");
        }
        this.config = config;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
            sendMessage.setText("Я тебя люблю!!!");
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException("Message wasn't send");
            }
        }
    }

    @Override
    public String getBotUsername() {
        return config.getName();
    }
    
}
