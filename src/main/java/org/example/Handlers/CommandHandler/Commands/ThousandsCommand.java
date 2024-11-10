package org.example.Handlers.CommandHandler.Commands;

import org.example.ArticleServices.IArticleFinder;
import org.example.Repositories.ISessionRepository;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;

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
        return List.of();
    }
}
