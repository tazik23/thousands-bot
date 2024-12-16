package org.example.DictionaryCompiler.WordComplexityAnalyzers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WordLengthAnalyzer implements IAnalyzer{
    private static final double coefficient = 0.01;

    @Override
    public double analyze(String word) {
        return word.length() * coefficient;
    }

    @Override
    public Map<String, Double> analyze(List<String> words) {
        return words.stream().collect(Collectors.toMap(
                w -> w,
                w -> analyze(w)
        ));
    }
}
