import org.example.ArticleServices.*;
import org.example.Bot.ThousandsBot;
import org.example.DictionaryCompiler.DictionaryCompiler;
import org.example.DictionaryCompiler.IDictionaryCompiler;
import org.example.DictionaryCompiler.TextTokenizer;
import org.example.DictionaryCompiler.WordComplexityAnalyzers.WordFrequencyAnalyzer;
import org.example.DictionaryCompiler.WordComplexityAnalyzers.WordLengthAnalyzer;
import org.example.Handlers.CallbackHandler.CallbackHandler;
import org.example.Handlers.CallbackHandler.CallbackType;
import org.example.Handlers.CallbackHandler.Callbacks.*;
import org.example.Handlers.CommandHandler.CommandHandler;
import org.example.Handlers.CommandHandler.CommandType;
import org.example.Handlers.CommandHandler.Commands.ICommand;
import org.example.Handlers.CommandHandler.Commands.StartCommand;
import org.example.Handlers.CommandHandler.Commands.ThousandsCommand;
import org.example.Handlers.IHandler;
import org.example.Repositories.ISessionRepository;
import org.example.Repositories.InMemorySessionRepository;
import org.example.Translator.ITranslator;
import org.example.Translator.Translator;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ISessionRepository sessionRepository = new InMemorySessionRepository();

        TextTokenizer tokenizer = new TextTokenizer();
        WordFrequencyAnalyzer frequencyAnalyzer = new WordFrequencyAnalyzer();
        WordLengthAnalyzer lengthAnalyzer = new WordLengthAnalyzer();

        ITranslator translator = new Translator("ru");
        IArticleParser articleParser = new ArticleParser();
        ThemesFinder themesFinder = new ThemesFinder();
        IArticleFinder articleFinder = new ArticleFinder();
        IDictionaryCompiler dictionaryCompiler = new DictionaryCompiler
                (tokenizer, lengthAnalyzer, frequencyAnalyzer, new Translator("ru"));

        Map<CallbackType, ICallback> callbacks = Map.of(
                CallbackType.ARTICLE, new ArticleCallback(sessionRepository, articleParser),
                CallbackType.TRANSLATION, new TranslationCallback(sessionRepository, translator),
                CallbackType.DICTIONARY, new DictionaryCallback(sessionRepository, dictionaryCompiler),
                CallbackType.THEME, new ThemesCallback(sessionRepository,  articleFinder)
        );

        Map<CommandType, ICommand> commands = Map.of(
                CommandType.START, new StartCommand(),
                CommandType.THOUSANDS, new ThousandsCommand(themesFinder, sessionRepository)
        );

        IHandler commandHandler = new CommandHandler(commands);
        IHandler callbackHandler= new CallbackHandler(callbacks);

        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new ThousandsBot(commandHandler, callbackHandler));
        }
        catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
