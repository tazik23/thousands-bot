package org.example.Handlers.CommandHandler;

import org.example.Handlers.CommandHandler.Commands.ICommand;
import org.example.Handlers.IHandler;
import org.example.Utils.Consts;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Map;

public class CommandHandler implements IHandler {
    private final Map<CommandType, ICommand> commands;

    public CommandHandler(Map<CommandType, ICommand> commands){
        this.commands = commands;
    }

    @Override
    public List<PartialBotApiMethod> handle(Update update) {
        Long chatId = update.getMessage().getChatId();
        String message = update.getMessage().getText();

        try {
            CommandType commandType = CommandType.fromDescription(message);
            ICommand command = commands.get(commandType);
            return command.apply(chatId);
        }
        catch (IllegalArgumentException e) {
            return List.of(new SendMessage(String.valueOf(chatId), Consts.UNKNOWN_COMMAND));
        }
    }
}
