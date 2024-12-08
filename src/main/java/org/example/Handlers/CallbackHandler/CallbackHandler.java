package org.example.Handlers.CallbackHandler;

import org.example.Handlers.CallbackHandler.Callbacks.ICallback;
import org.example.Handlers.IHandler;
import org.example.Utils.Consts;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
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
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        String callbackData = update.getCallbackQuery().getData();

        String[] parts = callbackData.split(" ", 2);

        String type = parts[0];
        String message = parts[1];

        try {
            CallbackType callbackType = CallbackType.fromDescription(type);
            ICallback callback = callbacks.get(callbackType);
            return callback.apply(chatId, message);
        }
        catch(IllegalArgumentException e){
            return List.of(new SendMessage(String.valueOf(chatId), Consts.ERROR));
        }
    }
}
