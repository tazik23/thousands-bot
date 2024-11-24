package org.example.Utils.io;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class DocxWriter implements AutoCloseable{
    private final XWPFDocument document;
    private final FileOutputStream fos;

    public DocxWriter(File file) throws IOException {
        this.fos = new FileOutputStream(file);
        this.document = new XWPFDocument();
    }

    public void writeParagraph(String text) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.createRun().setText(text);
    }

    public void write(List<String> text) {
        for (var paragraph : text){
            writeParagraph(paragraph);
        }
    }


    public void save() throws IOException {
        document.write(fos);
    }

    @Override
    public void close() throws Exception {
        if(document != null) {
            document.close();
        }
        if(fos != null) {
            fos.close();
        }
    }
}
