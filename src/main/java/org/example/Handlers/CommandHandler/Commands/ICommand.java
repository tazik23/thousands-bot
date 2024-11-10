package org.example.Handlers.CommandHandler.Commands;

import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;

import java.util.List;

public interface ICommand {
    public List<PartialBotApiMethod> apply(long id);
}
