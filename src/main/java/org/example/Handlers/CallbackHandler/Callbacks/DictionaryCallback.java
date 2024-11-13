package org.example.Handlers.CallbackHandler.Callbacks;

import org.example.DictionaryCompiler.IDictionaryCompiler;
import org.example.Models.Session;
import org.example.Repositories.ISessionRepository;
import org.example.Utils.Consts;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.io.File;
import java.util.ArrayList;
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
        Session session = repository.getSessionById(id);
        File selectedArticle = session.getSelectedArticle();
        File translatedArticle = session.getTranslatedArticle();
        File dictionary = session.getDictionary();

        List<PartialBotApiMethod> answers = new ArrayList<>();
        answers.add(new SendMessage(String.valueOf(id), Consts.ANSWER));

        if(message.equalsIgnoreCase("Да")) {
            if (selectedArticle == null) {
                return List.of(new SendMessage(String.valueOf(id), Consts.ERROR));
            }
            if (dictionary != null) {
                return List.of(new SendMessage(String.valueOf(id), Consts.DICTIONARY_EXISTS));
            }

            dictionary = dictionaryCompiler.compileDictionary(selectedArticle);
            session.setDictionary(dictionary);
        }

        if(selectedArticle != null)
            answers.add(new SendDocument(String.valueOf(id), new InputFile(selectedArticle)));
        if(translatedArticle != null)
            answers.add(new SendDocument(String.valueOf(id), new InputFile(translatedArticle)));
        if(dictionary != null)
            answers.add(new SendDocument(String.valueOf(id), new InputFile(dictionary)));

        repository.deleteSession(session);
        return answers;

    }
}
