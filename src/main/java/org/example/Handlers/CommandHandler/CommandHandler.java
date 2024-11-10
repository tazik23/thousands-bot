package org.example.Handlers.CommandHandler;

import org.example.Handlers.IHandler;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public class CommandHandler implements IHandler {
    @Override
    public List<PartialBotApiMethod> handle(Update update) {
        return List.of();
    }
}
