package util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.*;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.util.ArrayList;

public class PdfWriter extends DocumentWriter implements AutoCloseable {
    private PDDocument doc;
    private PDPage page;
    PDPageContentStream contentStream;
    float width;
    float height;

    ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
    int currentRow = -1;

    public PdfWriter(String title,String author) throws IOException{
        doc = new PDDocument();
        PDDocumentInformation info = new PDDocumentInformation();
        info.setAuthor(author);
        info.setCreator(author);
        info.setTitle(title);
        info.setSubject(title);
        doc.setDocumentInformation(info);

        page = new PDPage();
        doc.addPage(page);
        contentStream = new PDPageContentStream(doc, page);
        contentStream.setNonStrokingColor(Color.DARK_GRAY);
        width = page.getTrimBox().getWidth();
        height = page.getTrimBox().getHeight();
        contentStream.addRect(0, height-100, width, 100);
        contentStream.fill();
        try {
            contentStream.setNonStrokingColor(Color.WHITE);
            contentStream.beginText();
            PDFont font = PDType1Font.TIMES_BOLD;
            contentStream.setFont(font,30);
            contentStream.newLineAtOffset(10,height-30);
            contentStream.showText(title);
            contentStream.endText();
        } catch (IOException e) {
            e.printStackTrace();
        }


        contentStream.setNonStrokingColor(new Color(230,70,20));
        contentStream.addRect(0, 0, width, 50);
        contentStream.fill();

        try {
            contentStream.setNonStrokingColor(Color.WHITE);
            contentStream.beginText();
            PDFont font = PDType1Font.TIMES_BOLD;
            contentStream.setFont(font,20);
            contentStream.newLineAtOffset(10,10);
            contentStream.showText("Created by: " + author);
            contentStream.endText();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public PdfWriter v(String value) {
        table.get(currentRow).add(value);
        return this;
    }

    public PdfWriter t(String title) {
        try {
            contentStream.beginText();
            PDFont font = PDType1Font.TIMES_BOLD;
            contentStream.setFont(font,20);
            contentStream.newLineAtOffset(10,height-80);
            contentStream.showText(title);
            contentStream.endText();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this;
    }

    public PdfWriter h(String header) {
        table.get(0).add(header);
        return this;
    }

    public PdfWriter newLine() {
        table.add(new ArrayList<>());
        currentRow++;
        return this;
    }


    public PdfWriter flush() {
        float tableStartX = 5;
        float tableStartY = height-120;

        float rowWidth = 15;
        float tableHeight = table.size() * rowWidth;
        float tableWidth = width-10;
        int columnCount = table.get(0).size();
        float columnWidth = tableWidth / columnCount;
        for (int i = 0; i < columnCount+1; i++){
            try {
                contentStream.drawLine(
                        tableStartX + i*columnWidth,
                        tableStartY,
                        tableStartX + i*columnWidth,
                        tableStartY-tableHeight+rowWidth);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < table.size(); i++){
            try {
                contentStream.drawLine(
                        tableStartX,
                        tableStartY - i*rowWidth,
                        tableStartX+tableWidth,
                        tableStartY - i*rowWidth);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            contentStream.setNonStrokingColor(Color.BLACK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < table.size();i++) {
            ArrayList<String> row = table.get(i);
            for(int j = 0; j < row.size(); j++)
            {
                try {
                    contentStream.beginText();
                    PDFont font = PDType1Font.TIMES_BOLD;
                    contentStream.setFont(font,8);
                    contentStream.newLineAtOffset(tableStartX + j*columnWidth + 3,tableStartY - i*rowWidth - 10);
                    contentStream.showText(row.get(j));
                    contentStream.endText();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return this;
    }

    public void close() {

    }

    public byte[] getBytes() throws IOException{
        try(ByteArrayOutputStream stream = new ByteArrayOutputStream()){
            contentStream.close();
            doc.save(stream);
            stream.flush();
            return stream.toByteArray();
        }
        catch (IOException ex){}
        finally {
            doc.close();
        }
        return new byte[0];
    }
}
