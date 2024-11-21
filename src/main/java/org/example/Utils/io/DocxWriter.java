package org.example.Utils.io;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.FileOutputStream;
import java.io.IOException;

public class DocxWriter implements AutoCloseable{
    private XWPFDocument document;
    private FileOutputStream fos;

    public DocxWriter(FileOutputStream fileOutputStream) throws IOException {
        fos = fileOutputStream;
        document = new XWPFDocument();
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
