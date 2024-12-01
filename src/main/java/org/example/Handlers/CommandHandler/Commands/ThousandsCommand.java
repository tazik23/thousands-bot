package org.example.Handlers.CommandHandler.Commands;


import org.example.ArticleServices.IThemesFinder;
import org.example.Handlers.CallbackHandler.CallbackType;
import org.example.Models.Session;
import org.example.Repositories.ISessionRepository;
import org.example.Utils.Consts;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class ThousandsCommand implements ICommand{
    private final IThemesFinder themesFinder;
    private final ISessionRepository sessionRepository;

    public ThousandsCommand(IThemesFinder themesFinder, ISessionRepository sessionRepository){
        this.themesFinder = themesFinder;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public List<PartialBotApiMethod> apply(long id) {
        Session session = new Session(id);
        List<String> themes = themesFinder.getThemeNames();
        session.setSuggestedThemes(themes);
        sessionRepository.addSession(session);

        StringBuilder message = new StringBuilder(Consts.CHOOSE_THEME + "\n");

        for (int i = 1; i <= themes.size(); i++) {
            message.append(i).append(".) ").append(themes.get(i - 1)).append("\n");
        }

        SendMessage sendMessage = new SendMessage(String.valueOf(id), message.toString());
        sendMessage.setReplyMarkup(createKeyboard(themes));
        return List.of(sendMessage);
    }

    private InlineKeyboardMarkup createKeyboard(List<String> themes){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        for (int i = 0; i < themes.size(); i++) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(themes.get(i));
            button.setCallbackData(CallbackType.THEME.getDescription() + " " + i);
            rowList.add(List.of(button));
        }

        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }
}
