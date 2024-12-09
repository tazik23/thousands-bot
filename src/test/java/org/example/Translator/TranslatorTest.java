package org.example.Translator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;

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
}
