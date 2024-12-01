package org.example.DictionaryCompiler.WordComplexityAnalyzers;

public class WordLengthAnalyzer {
    private final double coefficient = 0.01;

    public double analyze(String word) {
        return word.length() * coefficient;
    }
}
