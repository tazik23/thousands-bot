package org.example.DictionaryCompiler.Queue;

import org.jetbrains.annotations.NotNull;

public class PrioritizedWord implements Comparable<PrioritizedWord> {
    private final String word;
    private final double priority;

    public PrioritizedWord(String word, double priority) {
        this.word = word;
        this.priority = priority;
    }

    public String getWord() {
        return word;
    }

    public double getPriority() {
        return priority;
    }

    @Override
    public int compareTo(@NotNull PrioritizedWord o) {
        return Double.compare(this.priority, o.priority);
    }
}