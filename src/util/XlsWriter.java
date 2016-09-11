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
        HSSFFont titleFont = workbook.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeight((short)30);
        titleStyle = workbook.createCellStyle();
        titleStyle.setFont(titleFont);

        HSSFFont headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setItalic(true);
        headerStyle = workbook.createCellStyle();
        headerStyle.setFont(titleFont);

        workbook = new HSSFWorkbook();
        sheet = workbook.createSheet(documentName);
        currentRow = sheet.createRow(rowNum++);
    }

    public XlsWriter v(String value) {
        Cell cell = currentRow.createCell(cellNum++);
        cell.setCellValue(value);
        return this;
    }

    public XlsWriter t(String title) {
        Cell cell = currentRow.createCell(cellNum++);
        cell.setCellStyle(titleStyle);
        cell.setCellValue(title);
        return this;
    }

    public XlsWriter h(String header) {
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
