package org.example.DictionaryCompiler;

import org.example.DictionaryCompiler.Queue.BoundedPriorityBlockingQueue;
import org.example.DictionaryCompiler.Queue.PrioritizedWord;
import org.example.DictionaryCompiler.WordComplexityAnalyzers.WordFrequencyAnalyzer;
import org.example.DictionaryCompiler.WordComplexityAnalyzers.WordLengthAnalyzer;
import org.example.Translator.ITranslator;
import org.example.Utils.io.DocxReader;
import org.example.Utils.io.DocxWriter;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DictionaryCompiler implements IDictionaryCompiler{
    private final TextTokenizer tokenizer;
    private final WordLengthAnalyzer lengthAnalyzer;
    private final WordFrequencyAnalyzer frequencyAnalyzer;
    private final ITranslator translator;

    public DictionaryCompiler(TextTokenizer tokenizer, WordLengthAnalyzer lengthAnalyzer, WordFrequencyAnalyzer frequencyAnalyzer, ITranslator translator) {
        this.tokenizer = tokenizer;
        this.lengthAnalyzer = lengthAnalyzer;
        this.frequencyAnalyzer = frequencyAnalyzer;
        this.translator = translator;
    }

    @Override
    public File compileDictionary(File file) {
        File dictionary = new File("Dictionary" + file.getName());
        BoundedPriorityBlockingQueue queue = getComplexWords(file);

        try(DocxWriter writer = new DocxWriter(dictionary)) {
            for(var prioritizedWord : queue) {
                String word = prioritizedWord.getWord();
                writer.writeParagraph(word + " - " + translator.translate(word));
            }
            writer.save();
            return dictionary;
        }
        catch (Exception e) {
            return null;
        }
    }

    private BoundedPriorityBlockingQueue getComplexWords(File file) {
        BoundedPriorityBlockingQueue queue = new BoundedPriorityBlockingQueue(150);
        Set<String> tokens = Collections.synchronizedSet(new HashSet<String>());

        try(DocxReader reader = new DocxReader(file)) {
            List<String> paragraphs = reader.read();

            paragraphs.stream()
                    .parallel()
                    .forEach(p -> tokens.addAll(tokenizer.tokenize(p)));

            tokens.stream()
                    .parallel()
                    .filter(t -> t != null && t.length() > 2)
                    .forEach(t -> {
                        double value = frequencyAnalyzer.analyze(t) + lengthAnalyzer.analyze(t);
                        queue.add(new PrioritizedWord(t, value));
                    });

            return queue;

        }
        catch (Exception e) {

        }
        return null;
    }
}
