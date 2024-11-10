package org.example.Handlers.CallbackHandler.Callbacks;

import org.example.Handlers.CallbackHandler.CallbackType;
import org.example.Repositories.ISessionRepository;
import org.example.Translator.ITranslator;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class TranslationCallback implements ICallback{
    private final ISessionRepository repository;
    private final ITranslator translator;

    public TranslationCallback(ISessionRepository repository, ITranslator translator){
        this.repository = repository;
        this.translator = translator;
    }

    @Override
    public List<PartialBotApiMethod> apply(long id, String message) {
        return List.of();
    }

    private InlineKeyboardMarkup createKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();

        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("Да");
        button1.setCallbackData(
                CallbackType.DICTIONARY.getDescription() + " " + "да");
        keyboardButtonsRow.add(button1);

        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("Нет");
        button2.setCallbackData(
                CallbackType.DICTIONARY.getDescription() + " " + "нет");
        keyboardButtonsRow.add(button2);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }
}
