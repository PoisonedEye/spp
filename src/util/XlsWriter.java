package util;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class XlsWriter extends DocumentWriter implements AutoCloseable {
    HSSFWorkbook workbook;
    HSSFSheet sheet;
    HSSFCellStyle titleStyle;
    HSSFCellStyle headerStyle;
    int rowNum;
    int cellNum;
    Row currentRow;

    public XlsWriter(String documentName){
        workbook = new HSSFWorkbook();
        HSSFFont titleFont = workbook.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short)12);
        titleStyle = workbook.createCellStyle();
        titleStyle.setFont(titleFont);

        HSSFFont headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setItalic(true);
        headerFont.setFontHeightInPoints((short)12);
        headerStyle = workbook.createCellStyle();
        headerStyle.setFont(titleFont);


        sheet = workbook.createSheet(documentName);
        sheet.setDefaultColumnWidth(30);
        currentRow = sheet.createRow(rowNum++);
    }

    public XlsWriter v(String value) {
        Cell cell = currentRow.createCell(cellNum++);
        cell.setCellValue(value);
        return this;
    }

    public XlsWriter t(String title) {
        currentRow.setHeightInPoints(30);
        Cell cell = currentRow.createCell(cellNum++);
        cell.setCellStyle(titleStyle);
        cell.setCellValue(title);
        return this;
    }

    public XlsWriter h(String header) {
        currentRow.setHeightInPoints(15);
        Cell cell = currentRow.createCell(cellNum++);
        cell.setCellStyle(headerStyle);
        cell.setCellValue(header);
        return this;
    }

    public XlsWriter newLine() {
        currentRow = sheet.createRow(rowNum++);
        cellNum = 0;
        return this;
    }


    public XlsWriter flush() {
        return this;
    }

    public void close() {}

    public byte[] getBytes(){
        return workbook.getBytes();
    }
}
