package org.example.DictionaryCompiler.Queue;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
@Getter
public class PrioritizedWord implements Comparable<PrioritizedWord> {
    private final String word;
    private final double priority;

    @Override
    public int compareTo(@NotNull PrioritizedWord o) {
        return Double.compare(this.priority, o.priority);
    }
}