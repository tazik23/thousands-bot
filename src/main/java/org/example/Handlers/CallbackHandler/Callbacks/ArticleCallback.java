package org.example.Handlers.CallbackHandler.Callbacks;

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

public class ArticleCallback implements ICallback{
    private final ISessionRepository sessionRepository;
    private final IArticleParser articleParser;

    public ArticleCallback(ISessionRepository sessionRepository, IArticleParser articleParser){
        this.sessionRepository = sessionRepository;
        this.articleParser = articleParser;
    }

    @Override
    public List<PartialBotApiMethod> apply(long id, String message) {
        Session session = sessionRepository.getSessionById(id);

        File selectedArticle = session.getSelectedArticle();

        if(selectedArticle != null){
            return List.of(new SendMessage(String.valueOf(id), Consts.ARTICLE_CHOSEN));
        }

        List<Article> articles = session.getSuggestedArticles();
        int num = Integer.parseInt(message);

        if(articles.isEmpty() || articles.size() < num){
            return List.of(new SendMessage(String.valueOf(id), Consts.ERROR));
        }
        try {
            selectedArticle = articleParser.parse(articles.get(num));
            session.setSelectedArticle(selectedArticle);

            SendMessage sendMessage = new SendMessage(String.valueOf(id), Consts.CHOOSE_TRANSLATION);
            sendMessage.setReplyMarkup(createKeyboard());
            return List.of(new SendMessage(String.valueOf(id), "выбрана статья "  + selectedArticle.getName()),sendMessage);
        }
        catch (IOException e) {
            return List.of(new SendMessage(String.valueOf(id), Consts.ERROR));
        }
    }

    private InlineKeyboardMarkup createKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();

        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("Да");
        button1.setCallbackData(
                CallbackType.TRANSLATION.getDescription() + " " + "да");
        keyboardButtonsRow.add(button1);

        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("Нет");
        button2.setCallbackData(
                CallbackType.TRANSLATION.getDescription() + " " + "нет");
        keyboardButtonsRow.add(button2);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }
}
