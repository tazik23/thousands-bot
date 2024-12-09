package org.example.Handlers.CallbackHandler.Callbacks;

import org.example.ArticleServices.IArticleFinder;
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
import java.util.Iterator;
import java.util.List;

public class ThemesCallback implements ICallback {
    private final ISessionRepository sessionRepository;
    private final IArticleFinder articleFinder;

    public ThemesCallback(ISessionRepository sessionRepository, IArticleFinder articleFinder) {
        this.sessionRepository = sessionRepository;
        this.articleFinder = articleFinder;
    }

    @Override
    public List<PartialBotApiMethod> apply(long id, String message) {
        Session session = sessionRepository.getSessionById(id);

        if (session.getSelectedArticle() != null) {
            return List.of(new SendMessage(String.valueOf(id), Consts.ARTICLE_CHOSEN));
        }


        if (message.equalsIgnoreCase("next")) {
            Iterator<Article> articleIterator = session.getSuggestedArticles();
            if (articleIterator == null || !articleIterator.hasNext()) {
                return List.of(new SendMessage(String.valueOf(id), "Нет больше статей"));
            }
            return createArticlesMessageWithButtons(id, "Вот следующие статьи:", articleIterator);
        }
        if (message.isEmpty()) {
            return List.of(new SendMessage(String.valueOf(id), Consts.ERROR));
        }


        Iterator<Article> articleIterator = articleFinder.findArticlesByTheme(message).iterator();
        session.setSuggestedArticles(articleIterator);

        String text = "Вы выбрали тему: " + message + "\n" + Consts.CHOOSE_ARTICLE;

        return createArticlesMessageWithButtons(id, text, articleIterator);
    }

    private List<PartialBotApiMethod> createArticlesMessageWithButtons(long id, String text, Iterator<Article> articleIterator) {
        StringBuilder textBuilder = new StringBuilder(text + "\n");
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        int counter = 0;
        while (articleIterator.hasNext() && counter < 5) {
            Article article = articleIterator.next();

            String Link = '(' + escapeMarkdown(article.getLink()) + ") ";
            String Title = '[' + escapeMarkdown(article.getTitle()) + ']';
            textBuilder.append(counter + 1).append("\\) ").append(Title).append(Link).append("\n");


            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(article.getTitle());


            int length = article.getTitle().codePointCount(0, article.getTitle().length());
            String safeTitle = length > 56
                    ? article.getTitle().substring(0, 56)
                    : article.getTitle();

            button.setCallbackData(CallbackType.ARTICLE.getDescription() + " " + safeTitle);
            rowList.add(List.of(button));
            counter++;
        }


        if (articleIterator.hasNext()) {
            InlineKeyboardButton nextButton = new InlineKeyboardButton();
            nextButton.setText("Еще");
            nextButton.setCallbackData(CallbackType.THEME.getDescription() + ' ' + "next");
            rowList.add(List.of(nextButton));
        }

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(id);
        sendMessage.setParseMode("MarkdownV2");
        sendMessage.setText(textBuilder.toString());
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowList);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        return List.of(sendMessage);
    }

    private String escapeMarkdown(String text) {
        return text.replace("(", "\\(")
                .replace(")", "\\)")
                .replace("[", "\\[")
                .replace("]", "\\]")
                .replace("~", "\\~")
                .replace("`", "\\`")
                .replace(">", "\\>")
                .replace("#", "\\#")
                .replace("+", "\\+")
                .replace("-", "\\-")
                .replace("=", "\\=")
                .replace("!", "\\!")
                .replace("_", "\\_")
                .replace("*", "\\*")
                .replace(".","\\.");
    }
}
