package org.example.Utils.io;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class DocxReader {
    private XWPFDocument document;
    private Iterator<XWPFParagraph> paragraphIterator;

    public DocxReader(FileInputStream fis) throws IOException {
        document = new XWPFDocument(fis);
        paragraphIterator = document.getParagraphs().iterator();
    }

}
