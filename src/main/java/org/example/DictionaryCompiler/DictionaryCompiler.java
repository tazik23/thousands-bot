package org.example.DictionaryCompiler;

import org.example.DictionaryCompiler.WordComplexityAnalyzers.WordFrequencyAnalyzer;
import org.example.DictionaryCompiler.WordComplexityAnalyzers.WordLengthAnalyzer;

import java.io.File;

public class DictionaryCompiler implements IDictionaryCompiler{
    private final TextTokenizer tokenizer;
    private final WordLengthAnalyzer lengthAnalyzer;
    private final WordFrequencyAnalyzer frequencyAnalyzer;

    public DictionaryCompiler(TextTokenizer tokenizer, WordLengthAnalyzer lengthAnalyzer, WordFrequencyAnalyzer frequencyAnalyzer) {
        this.tokenizer = tokenizer;
        this.lengthAnalyzer = lengthAnalyzer;
        this.frequencyAnalyzer = frequencyAnalyzer;
    }

    @Override
    public File compileDictionary(File file) {
        return null;
    }
}
