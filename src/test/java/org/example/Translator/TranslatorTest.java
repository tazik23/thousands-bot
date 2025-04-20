package org.example.Translator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.*;

class TranslatorTest {
    private YandexTranslateClient mockClient;
    private Translator translator;

    @BeforeEach
    public void setUp() {
        mockClient = Mockito.mock(YandexTranslateClient.class);
        translator = new Translator(mockClient);
    }

    @Test
    void testTranslateString() throws IOException {
        String word = "World";
        String translatedWord = "Мир";

        Mockito.when(mockClient.sendRequest(List.of(word)))
                .thenReturn(List.of(translatedWord));

        String result = translator.translate(word);

        Assertions.assertEquals(result, translatedWord);
    }

    @Test
    void testTranslateEmptyString() {
        Assertions.assertThrows(IOException.class, () -> translator.translate(""));
    }

    @Test
    void testTranslateList() throws IOException {
        List<String> words = List.of("Hello world", "Goodbye DPI");
        List<String> translatedWords = List.of("Привет, мир", "Прощай, DPI");

        Mockito.when(mockClient.sendRequest(words))
                .thenReturn(translatedWords);

        List<String> result = translator.translate(words);

        Assertions.assertEquals(result, translatedWords);
    }

    @Test
    void testTranslateEmptyList() {
        Assertions.assertThrows(IOException.class, () -> translator.translate(Collections.emptyList()));
    }

    @Test
    void testTranslateNull() {
        List<String> nullList = null;
        Assertions.assertThrows(IOException.class, () -> translator.translate(nullList));
    }

    @Test
    void testTranslateLargeText() throws IOException {
        List<String> words = List.of(
                "Hello ".repeat(1000), "world ".repeat(1000), "Hello world"
        );
        Mockito.when(mockClient.sendRequest(Mockito.anyList()))
                .thenReturn(List.of("translated text"));

        List<String> result = translator.translate(words);

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());

        Mockito.verify(mockClient, Mockito.times(2))
                .sendRequest(Mockito.anyList());
    }
}
