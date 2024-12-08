package org.example.Handlers.CallbackHandler.Callbacks;

import org.example.ArticleServices.IArticleFinder;
import org.example.ArticleServices.IThemesFinder;
import org.example.Handlers.CallbackHandler.CallbackType;
import org.example.Models.Article;
import org.example.Models.Session;
import org.example.Repositories.ISessionRepository;
import org.example.Utils.Consts;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class ThemesCallback implements ICallback{
    private final ISessionRepository sessionRepository;
    private  final IArticleFinder articleFinder;

    public ThemesCallback(ISessionRepository sessionRepository, IArticleFinder articleFinder ){
        this.sessionRepository = sessionRepository;
        this.articleFinder = articleFinder;
    }

    @Override
    public List<PartialBotApiMethod> apply(long id, String message) {
        Session session = sessionRepository.getSessionById(id);

        if (session.getSelectedArticle() != null) {
            return List.of(new SendMessage(String.valueOf(id), Consts.ARTICLE_CHOSEN));
        }

        List<String> themes = session.getSuggestedThemes();

        int themeIndex;
        try {
            themeIndex = Integer.parseInt(message);
        } catch (NumberFormatException e) {
            return List.of(new SendMessage(String.valueOf(id), Consts.ERROR));
        }

        if (themeIndex < 0 || themeIndex >= themes.size()) {
            return List.of(new SendMessage(String.valueOf(id), Consts.ERROR));
        }

        String selectedTheme = themes.get(themeIndex);
        session.setSelectedTheme(selectedTheme);

        List<Article> articles = articleFinder.findArticlesByTheme(selectedTheme);
        if (articles.isEmpty()) {
            return List.of(new SendMessage(String.valueOf(id), Consts.ERROR));
        }
        session.setSuggestedArticles(articles);

        SendMessage sendMessage = new SendMessage(String.valueOf(id), Consts.CHOOSE_ARTICLE);
        sendMessage.setReplyMarkup(createKeyboard(articles));

        String text = "Вы выбрали тему: " + selectedTheme + "\n";
        return List.of(new SendMessage(String.valueOf(id), text), sendMessage);
    }


    private InlineKeyboardMarkup createKeyboard(List<Article> articles) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        for (int i = 0; i < articles.size(); i++) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(articles.get(i).getTitle());
            button.setCallbackData(CallbackType.ARTICLE.getDescription() + " " + i);
            rowList.add(List.of(button));
        }

        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }
}
