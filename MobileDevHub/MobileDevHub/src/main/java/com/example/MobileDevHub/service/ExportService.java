package com.example.MobileDevHub.service;

import com.example.MobileDevHub.repository.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.persistence.Entity;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
@Slf4j
@Service
public class ExportService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ManufacturesRepository manufacturesRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private MobileRepository mobileRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ShipmentRepository shipmentRepository;

    public ByteArrayOutputStream exportToExcel(String entityType) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(entityType);

        List<?> data = getEntityData(entityType);
        if (data.isEmpty()) {
            throw new RuntimeException("No data found for the specified entity type.");
        }

        // Create header row
        Row headerRow = sheet.createRow(0);
        createHeaderRow(data.get(0), headerRow);

        // Create data rows
        int rowNum = 1;
        for (Object record : data) {
            Row row = sheet.createRow(rowNum++);
            createDataRow(record, row);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to export data to Excel", e);
        }

        return outputStream;
    }
    private void createHeaderRow(Object record, Row headerRow) {
        Field[] fields = getAllFields(record.getClass());
        int colNum = 0;
        for (Field field : fields) {
            field.setAccessible(true);
            Cell cell = headerRow.createCell(colNum++);
            cell.setCellValue(field.getName());
            cell.setCellStyle(createHeaderCellStyle(headerRow.getSheet().getWorkbook()));
        }
    }

    private void createDataRow(Object record, Row row) {
        Field[] fields = getAllFields(record.getClass()); // Use the getAllFields method from the previous example
        int colNum = 0;
        for (Field field : fields) {
            field.setAccessible(true);
            Cell cell = row.createCell(colNum++);
            try {
                Object value = field.get(record);
                if (value != null) {
                    if (isEntity(value)) {
                        // If the field is an entity, you can choose to print a specific property, e.g., ID or name
                        cell.setCellValue(getEntityIdOrName(value));
                    } else {
                        cell.setCellValue(value.toString());
                    }
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Failed to access data for Excel export", e);
            }
        }
    }

    private boolean isEntity(Object value) {
        return value.getClass().isAnnotationPresent(Entity.class);
    }

    private String getEntityIdOrName(Object entity) {
        try {
            // Assuming the entity has a getId() method
            Method method = entity.getClass().getMethod("getId");
            return method.invoke(entity).toString();
        } catch (NoSuchMethodException e) {
            // Fallback to getName() or similar
            try {
                Method nameMethod = entity.getClass().getMethod("getName");
                return nameMethod.invoke(entity).toString();
            } catch (Exception ex) {
                // If no getId() or getName() method, handle it accordingly
                return "N/A";
            }
        } catch (Exception e) {
            // If no getId() method, fall back to a safe approach
            return "N/A";
        }
    }



    private CellStyle createHeaderCellStyle(Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        cellStyle.setFont(font);
        return cellStyle;
    }

    private Field[] getAllFields(Class<?> type) {
        List<Field> fields = new ArrayList<>();
        while (type != null && type != Object.class) {
            for (Field field : type.getDeclaredFields()) {
                // Avoid adding fields that are likely to cause recursion
                if (!field.isAnnotationPresent(JsonIgnore.class) && !Collection.class.isAssignableFrom(field.getType())) {
                    fields.add(field);
                }
            }
            type = type.getSuperclass();
        }
        return fields.toArray(new Field[0]);
    }


    private List<?> getEntityData(String entityType) {
        try {
            switch (entityType.toLowerCase()) {
                case "employee":
                    return employeeRepository.findAll();
                case "manufacturer":
                    return manufacturesRepository.findAll();
                case "supplier":
                    return supplierRepository.findAll();
                case "mobile":
                    return mobileRepository.findAll();
                case "order":
                    return orderRepository.findAll();
                case "invoice":
                    return invoiceRepository.findAll();
                case "shipment":
                    return shipmentRepository.findAll();
                default:
                    throw new IllegalArgumentException("Invalid entity type");
            }
        } catch (Exception e) {
            // Log the exception
            log.error("Error retrieving data for entity type {}: {}", entityType, e.getMessage());
            throw e;
        }
    }


    public ByteArrayOutputStream exportToPDF(String entityType) {
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, outputStream);
            document.open();

            List<?> data = getEntityData(entityType);
            if (data.isEmpty()) {
                throw new RuntimeException("No data found for the specified entity type.");
            }

            PdfPTable table = createPdfTable(data);
            document.add(table);
            document.close();

        } catch (DocumentException e) {
            throw new RuntimeException("Failed to export data to PDF", e);
        }

        return outputStream;
    }

    private PdfPTable createPdfTable(List<?> data) throws DocumentException {
        Field[] fields = getAllFields(data.get(0).getClass());
        PdfPTable table = new PdfPTable(fields.length);

        for (Field field : fields) {
            PdfPCell headerCell = new PdfPCell(new Phrase(field.getName()));
            headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(headerCell);
        }

        for (Object record : data) {
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    Object value = field.get(record);
                    if (value != null) {
                        if (isEntity(value)) {
                            table.addCell(getEntityIdOrName(value));
                        } else {
                            table.addCell(value.toString());
                        }
                    } else {
                        table.addCell(""); // Handle null values
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Failed to access data for PDF export", e);
                }
            }
        }

        return table;
    }

}
