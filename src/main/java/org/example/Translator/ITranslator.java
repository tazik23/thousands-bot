package org.example.Translator;

import java.io.File;
import java.io.IOException;

public interface ITranslator {
    String translate(String text) throws IOException;
    File translateFile(File file) throws IOException;
}
