package org.example.DictionaryCompiler.WordComplexityAnalyzers;

import java.util.List;
import java.util.Map;

public interface IAnalyzer {
    double analyze(String word);
    Map<String, Double> analyze(List<String> words);
}
