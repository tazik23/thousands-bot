package org.example.Translator;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ITranslator {
    List<String> translate(List<String> text) throws IOException;
    File translateFile(File file) throws IOException;
}
