package org.example.Handlers.CallbackHandler.Callbacks;

import org.example.Repositories.ISessionRepository;
import org.example.Translator.ITranslator;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;

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
        return List.of();
    }
}
