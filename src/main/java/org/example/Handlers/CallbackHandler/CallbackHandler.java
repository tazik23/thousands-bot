package org.example.Handlers.CallbackHandler;

import org.example.Handlers.CallbackHandler.Callbacks.ICallback;
import org.example.Handlers.IHandler;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Map;

public class CallbackHandler implements IHandler {
    private final Map<CallbackType, ICallback> callbacks;

    public CallbackHandler(Map<CallbackType, ICallback> callbacks){
        this.callbacks = callbacks;
    }

    @Override
    public List<PartialBotApiMethod> handle(Update update) {
        return List.of();
    }
}
