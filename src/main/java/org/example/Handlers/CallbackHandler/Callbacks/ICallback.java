package org.example.Handlers.CallbackHandler.Callbacks;

import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;

import java.util.List;

public interface ICallback {
    public List<PartialBotApiMethod> apply(long id, String message);
}
