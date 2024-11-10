package org.example.Bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class ThousandsBot extends TelegramLongPollingBot {
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
        
    }
}
