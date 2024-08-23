package com.example.MobileDevHub.util;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class PDFExporter {

    public byte[] exportToPDF(List<Map<String, Object>> data) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Load a standard font
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);

        // Add table headers
        Table table = new Table(data.get(0).keySet().size());
        for (String header : data.get(0).keySet()) {
            table.addCell(new Cell().add(new Paragraph(header).setFont(font)));
        }

        // Add table rows
        for (Map<String, Object> rowData : data) {
            for (Object cellValue : rowData.values()) {
                table.addCell(new Cell().add(new Paragraph(cellValue.toString()).setFont(font)));
            }
        }

        document.add(table);
        document.close();

        return outputStream.toByteArray();
    }
}
