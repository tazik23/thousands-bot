package org.example.Handlers.CallbackHandler.Callbacks;

import org.example.DictionaryCompiler.IDictionaryCompiler;
import org.example.Repositories.ISessionRepository;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;

import java.util.List;

public class DictionaryCallback implements ICallback{
    private final ISessionRepository repository;
    private final IDictionaryCompiler dictionaryCompiler;

    public DictionaryCallback(ISessionRepository repository, IDictionaryCompiler compiler){
        this.repository = repository;
        this.dictionaryCompiler = compiler;
    }

    @Override
    public List<PartialBotApiMethod> apply(long id, String message) {
        return List.of();
    }
}
