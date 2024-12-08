package org.example.Handlers.CallbackHandler.Callbacks;

import org.example.ArticleServices.IArticleFinder;
import org.example.ArticleServices.IArticleParser;
import org.example.Handlers.CallbackHandler.CallbackType;
import org.example.Models.Article;
import org.example.Models.Session;
import org.example.Repositories.ISessionRepository;
import org.example.Utils.Consts;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArticleCallback implements ICallback {
    private final ISessionRepository sessionRepository;
    private final IArticleParser articleParser;
    private final IArticleFinder articleFinder;

    public ArticleCallback(ISessionRepository sessionRepository, IArticleParser articleParser, IArticleFinder articleFinder) {
        this.sessionRepository = sessionRepository;
        this.articleParser = articleParser;
        this.articleFinder = articleFinder;
    }

    @Override
    public List<PartialBotApiMethod> apply(long id, String message) {

        Session session = sessionRepository.getSessionById(id);
        File selectedArticle = session.getSelectedArticle();

        if (selectedArticle != null) {
            return List.of(new SendMessage(String.valueOf(id), Consts.ARTICLE_CHOSEN));
        }

        Article selectedArticleObject = articleFinder.findArticleByTitle(message);
        if (selectedArticleObject == null) {
            return List.of(new SendMessage(String.valueOf(id), Consts.ERROR));
        }

        try {
            selectedArticle = articleParser.parse(selectedArticleObject);
            session.setSelectedArticle(selectedArticle);

            SendMessage sendMessage = new SendMessage(String.valueOf(id), Consts.CHOOSE_TRANSLATION);
            sendMessage.setReplyMarkup(createKeyboard());

            return List.of(new SendMessage(String.valueOf(id), "Выбрана статья: " + selectedArticle.getName()), sendMessage);

        } catch (IOException e) {
            return List.of(new SendMessage(String.valueOf(id), Consts.ERROR));
        }
    }

    private InlineKeyboardMarkup createKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();


        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("Да");
        button1.setCallbackData(CallbackType.TRANSLATION.getDescription() + " да");
        keyboardButtonsRow.add(button1);

        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("Нет");
        button2.setCallbackData(CallbackType.TRANSLATION.getDescription() + " нет");
        keyboardButtonsRow.add(button2);

        rowList.add(keyboardButtonsRow);
        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }
}
