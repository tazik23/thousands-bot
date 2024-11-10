package org.example.Handlers.CommandHandler.Commands;

import org.example.Utils.Consts;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

public class StartCommand implements ICommand{
    @Override
    public List<PartialBotApiMethod> apply(long id) {
        return List.of(new SendMessage(String.valueOf(id), Consts.START_MESSAGE));
    }
}
