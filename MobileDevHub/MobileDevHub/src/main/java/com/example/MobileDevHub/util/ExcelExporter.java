package com.example.MobileDevHub.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class ExcelExporter {

    public byte[] exportToExcel(List<Map<String, Object>> data) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");
        Row headerRow = sheet.createRow(0);

        // Create headers
        int cellIndex = 0;
        if (!data.isEmpty()) {
            for (String header : data.get(0).keySet()) {
                headerRow.createCell(cellIndex++).setCellValue(header);
            }
        }

        // Populate data
        int rowIndex = 1;
        for (Map<String, Object> rowData : data) {
            Row row = sheet.createRow(rowIndex++);
            cellIndex = 0;
            for (Object cellValue : rowData.values()) {
                row.createCell(cellIndex++).setCellValue(cellValue.toString());
            }
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();
        return outputStream.toByteArray();
    }
}
