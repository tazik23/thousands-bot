package org.example.DictionaryCompiler;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class TextTokenizer {
    private final StanfordCoreNLP pipeline;

    public TextTokenizer() {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma");
        props.setProperty("outputFormat", "text");
        pipeline = new StanfordCoreNLP(props);

    }

    public Set<String> tokenize(String text) {
        CoreDocument doc = new CoreDocument(text);
        pipeline.annotate(doc);

        Set<String> uniqueWords = new HashSet<>();

        for (CoreLabel token : doc.tokens()) {
            uniqueWords.add(token.lemma().toLowerCase());
        }
        return uniqueWords;
    }
}
