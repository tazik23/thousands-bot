package org.example.Handlers;

import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public interface IHandler {
    List<PartialBotApiMethod> handle(Update update);
}

