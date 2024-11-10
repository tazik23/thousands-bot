package org.example.Handlers.CommandHandler.Commands;

import org.example.ArticleServices.IArticleFinder;
import org.example.Models.Article;
import org.example.Models.Session;
import org.example.Repositories.ISessionRepository;
import org.example.Utils.Consts;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

public class ThousandsCommand implements ICommand{
    private final IArticleFinder articleFinder;
    private final ISessionRepository sessionRepository;

    public ThousandsCommand(IArticleFinder articleFinder, ISessionRepository sessionRepository){
        this.articleFinder = articleFinder;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public List<PartialBotApiMethod> apply(long id) {
        Session session = new Session(id);
        List<Article> articles = articleFinder.findArticles();
        session.setSuggestedArticles(articles);
        sessionRepository.addSession(session);

        StringBuilder message = new StringBuilder(Consts.CHOOSE_ARTICLE + "\n");

        for(int i = 1; i <= articles.size(); i++)
        {
            message.append(i + ".) " + articles.get(i - 1).getTitle() + "\n");
        }
        SendMessage sendMessage = new SendMessage(String.valueOf(id), message.toString());
        return List.of();
    }
}
