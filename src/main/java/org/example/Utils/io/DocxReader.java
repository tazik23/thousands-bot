package org.example.Utils.io;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class DocxReader implements AutoCloseable {
    private final FileInputStream fis;
    private final XWPFDocument document;
    private final Iterator<XWPFParagraph> paragraphIterator;

    public DocxReader(File file) throws IOException {
        this.fis = new FileInputStream(file);
        this.document = new XWPFDocument(fis);
        this.paragraphIterator = document.getParagraphs().iterator();
    }

    public String readParagraph() throws IOException {
        if (paragraphIterator.hasNext()) {
            return paragraphIterator.next().getText();
        }
        return null;
    }

    @Override
    public void close() throws Exception {
        if(document != null) {
            document.close();
        }
        if(fis != null){
            fis.close();
        }
    }
}
