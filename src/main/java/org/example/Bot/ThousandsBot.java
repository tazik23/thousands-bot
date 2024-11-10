package org.example.Bot;

import org.example.Handlers.IHandler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ThousandsBot extends TelegramLongPollingBot {
    private final IHandler commandHandler;
    private final IHandler callbackHandler;

    public ThousandsBot(IHandler commandHandler, IHandler callbackHandler){
        this.commandHandler = commandHandler;
        this.callbackHandler = callbackHandler;
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
        
    }

    private void sendMessage(SendMessage sendMessage){
        try {
            execute(sendMessage);
        }
        catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendFile(SendDocument sendDocument) {
        try {
            execute(sendDocument);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
