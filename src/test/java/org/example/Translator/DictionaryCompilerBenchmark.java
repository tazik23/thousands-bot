package org.example.Translator;

import org.example.DictionaryCompiler.DictionaryCompiler;
import org.example.DictionaryCompiler.IDictionaryCompiler;
import org.example.DictionaryCompiler.TextTokenizer;
import org.example.DictionaryCompiler.WordComplexityAnalyzers.IAnalyzer;
import org.example.DictionaryCompiler.WordComplexityAnalyzers.WordComplexityAnalyzer;
import org.example.DictionaryCompiler.WordComplexityAnalyzers.WordFrequencyAnalyzer;
import org.example.DictionaryCompiler.WordComplexityAnalyzers.WordLengthAnalyzer;
import org.mockito.Mockito;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.All)
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(iterations = 2)
@Measurement(iterations = 2)
@State(Scope.Thread)
public class DictionaryCompilerBenchmark {
    @Benchmark
    public void testCompileDictionary5000Chars() throws IOException {
        TextTokenizer tokenizer = new TextTokenizer();
        IAnalyzer analyzer = new WordComplexityAnalyzer
                (List.of(new WordLengthAnalyzer(), new WordFrequencyAnalyzer()));
        ITranslator translator = Mockito.mock(Translator.class);
        Mockito.when(translator.translate(Mockito.anyList())).thenReturn(List.of("1", "2", "3"));
        IDictionaryCompiler compiler = new DictionaryCompiler(tokenizer, analyzer, translator);
        compiler.compileDictionary(new File("C:\\Users\\goag\\Desktop\\a.docx"));
    }

    @Benchmark
    public void testCompileDictionary10000Chars() throws IOException {
        TextTokenizer tokenizer = new TextTokenizer();
        IAnalyzer analyzer = new WordComplexityAnalyzer
                (List.of(new WordLengthAnalyzer(), new WordFrequencyAnalyzer()));
        ITranslator translator = Mockito.mock(Translator.class);
        Mockito.when(translator.translate(Mockito.anyList())).thenReturn(List.of("1", "2", "3"));
        IDictionaryCompiler compiler = new DictionaryCompiler(tokenizer, analyzer, translator);
        compiler.compileDictionary(new File("C:\\Users\\goag\\Desktop\\b.docx"));
    }

    @Benchmark
    public void testCompileDictionary15000Chars() throws IOException {
        TextTokenizer tokenizer = new TextTokenizer();
        IAnalyzer analyzer = new WordComplexityAnalyzer
                (List.of(new WordLengthAnalyzer(), new WordFrequencyAnalyzer()));
        ITranslator translator = Mockito.mock(Translator.class);
        Mockito.when(translator.translate(Mockito.anyList())).thenReturn(List.of("1", "2", "3"));
        IDictionaryCompiler compiler = new DictionaryCompiler(tokenizer, analyzer, translator);
        compiler.compileDictionary(new File("C:\\Users\\goag\\Desktop\\c.docx"));
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(DictionaryCompilerBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
