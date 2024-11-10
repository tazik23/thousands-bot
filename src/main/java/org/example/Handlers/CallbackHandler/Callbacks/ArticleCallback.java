package org.example.Handlers.CallbackHandler.Callbacks;

import org.example.ArticleServices.IArticleParser;
import org.example.Repositories.ISessionRepository;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;

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
        return List.of();
    }
}
