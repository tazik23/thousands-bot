package org.example.Utils.io;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class DocxWriter implements AutoCloseable{
    private final XWPFDocument document;
    private final FileOutputStream fos;

    public DocxWriter(File file) throws IOException {
        this.fos = new FileOutputStream(file);
        this.document = new XWPFDocument();
    }

    public void writeParagraph(String text) throws IOException {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.createRun().setText(text);
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
