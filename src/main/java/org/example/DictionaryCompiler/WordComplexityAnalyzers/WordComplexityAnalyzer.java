package org.example.DictionaryCompiler.WordComplexityAnalyzers;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public class WordComplexityAnalyzer implements IAnalyzer{
    private List<IAnalyzer> analyzers;

    @Override
    public double analyze(String word) {
        return analyzers.stream()
                .map(analyzer -> analyzer.analyze(word))
                .reduce(0.0, Double::sum);
    }

    @Override
    public Map<String, Double> analyze(List<String> words) {
        return analyzers.stream()
                .parallel()
                .map(analyzer -> analyzer.analyze(words))
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        Double::sum
                ));
    }
}
