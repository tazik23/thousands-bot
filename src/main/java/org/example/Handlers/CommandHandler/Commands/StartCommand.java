package org.example.Handlers.CommandHandler.Commands;

import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;

import java.util.List;

public class StartCommand implements ICommand{
    @Override
    public List<PartialBotApiMethod> apply(long id) {
        return List.of();
    }
}
