package org.example.Handlers.CallbackHandler.Callbacks;

import org.example.Handlers.CallbackHandler.CallbackType;
import org.example.Models.Session;
import org.example.Repositories.ISessionRepository;
import org.example.Translator.ITranslator;
import org.example.Utils.Consts;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.io.File;
import java.io.IOException;
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
        if(message.equalsIgnoreCase("Да")) {
            Session session = repository.getSessionById(id);
            File selectedArticle = session.getSelectedArticle();
            File translatedArticle = session.getTranslatedArticle();

            if (selectedArticle == null) {
                return List.of(new SendMessage(String.valueOf(id), Consts.ERROR));
            }
            if (translatedArticle != null) {
                return List.of(new SendMessage(String.valueOf(id), Consts.TRANSLATION_EXISTS));
            }
            try {
                translatedArticle = translator.translateFile(selectedArticle);
                session.setTranslatedArticle(translatedArticle);
            }
            catch (IOException e){
                return List.of(new SendMessage(String.valueOf(id), Consts.ERROR));
            }
        }

        SendMessage sendMessage =  new SendMessage(String.valueOf(id), Consts.CHOOSE_DICTIONARY);
        sendMessage.setReplyMarkup(createKeyboard());

        return List.of(sendMessage);
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
