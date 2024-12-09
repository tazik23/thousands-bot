package org.example.DictionaryCompiler;

import lombok.AllArgsConstructor;
import org.example.DictionaryCompiler.Queue.BoundedPriorityBlockingQueue;
import org.example.DictionaryCompiler.Queue.PrioritizedWord;
import org.example.DictionaryCompiler.WordComplexityAnalyzers.WordFrequencyAnalyzer;
import org.example.DictionaryCompiler.WordComplexityAnalyzers.WordLengthAnalyzer;
import org.example.Translator.ITranslator;
import org.example.Utils.io.DocxReader;
import org.example.Utils.io.DocxWriter;

import java.io.File;
import java.util.*;

@AllArgsConstructor
public class DictionaryCompiler implements IDictionaryCompiler{
    private final TextTokenizer tokenizer;
    private final WordLengthAnalyzer lengthAnalyzer;
    private final WordFrequencyAnalyzer frequencyAnalyzer;
    private final ITranslator translator;

    @Override
    public File compileDictionary(File file) {
        File dictionary = new File("Dictionary_" + file.getName());
        BoundedPriorityBlockingQueue queue = getComplexWords(file);


        try(DocxWriter writer = new DocxWriter(dictionary)) {
            List<String> words = new ArrayList<>();
            for(var prioritizedWord : queue) {
                String word = prioritizedWord.getWord();
                words.add(word);
            }
            List<String> translations = translator.translate(words);

            Iterator<PrioritizedWord> it1 = queue.iterator();
            Iterator<String> it2 = translations.iterator();

            while (it1.hasNext() && it2.hasNext()) {
                writer.writeParagraph(it1.next().getWord() + "-" + it2.next());
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
