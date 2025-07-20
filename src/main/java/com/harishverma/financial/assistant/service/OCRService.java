package com.harishverma.financial.assistant.service;

import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Service
@RequiredArgsConstructor
public class OCRService {

    public String extractText(byte[] fileData, String contentType) throws IOException {
        if (contentType.equals("application/pdf")) {
            return extractTextFromPDF(fileData);
        } else if (contentType.startsWith("image/")) {
            return extractTextFromImage(fileData);
        } else {
            throw new UnsupportedOperationException("Unsupported file type: " + contentType);
        }
    }

    private String extractTextFromPDF(byte[] fileData) throws IOException {
        try (PDDocument document = PDDocument.load(fileData)) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        }
    }

    private String extractTextFromImage(byte[] fileData) {
        try {
            File tempImage = File.createTempFile("ocr-", ".tmp");
            try (OutputStream os = new FileOutputStream(tempImage)) {
                os.write(fileData);
            }

            Tesseract tesseract = new Tesseract();
            tesseract.setDatapath("/usr/share/tesseract-ocr/4.00/tessdata"); // ✅ Update to your system path
            return tesseract.doOCR(tempImage);
        } catch (IOException | TesseractException e) {
            throw new RuntimeException("OCR failed: " + e.getMessage(), e);
        }
    }
}
