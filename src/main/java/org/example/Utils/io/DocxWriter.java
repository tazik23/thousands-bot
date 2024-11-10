package org.example.Utils.io;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileOutputStream;
import java.io.IOException;

public class DocxWriter {
    private XWPFDocument document;
    private FileOutputStream fos;

    public DocxWriter(FileOutputStream fileOutputStream) throws IOException {
        fos = fileOutputStream;
        document = new XWPFDocument();
    }

}
