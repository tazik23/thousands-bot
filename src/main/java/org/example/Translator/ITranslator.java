package org.example.Translator;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ITranslator {
    String translate(String text) throws IOException;
    List<String> translate(List<String> text) throws IOException;
    File translateFile(File file) throws IOException;
}
