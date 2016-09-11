package util;

import java.io.*;

public class CsvWriter extends DocumentWriter implements AutoCloseable {
    private ByteArrayOutputStream stream;

    private char delimiter = ';';

    private boolean first = true;

    public CsvWriter(){
        this.stream = new ByteArrayOutputStream();
    }

    public CsvWriter v(String value) {
        if (!first) string("" + delimiter);
        string(escape(value));
        first = false;
        return this;
    }

    public CsvWriter t(String title) {
        return v(title);
    }

    public CsvWriter h(String header) {
        return v(header);
    }

    public CsvWriter newLine() {
        first = true;
        return string("\n");
    }


    public CsvWriter flush() {
        try {
            if (stream != null) {
                Flushable flushable = (Flushable) stream;
                flushable.flush();
            }
        } catch (java.io.IOException e) { }
        return this;
    }

    public void close() {
        try {
            if (stream != null) {
                Closeable closeable = (Closeable) stream;
                closeable.close();
            }
        } catch (java.io.IOException e) { }
    }

    private CsvWriter string(String s) {
        try {
            stream.write(s.getBytes("windows-1251"));
        } catch (java.io.IOException e) { }
        return this;
    }

    private String escape(String value) {
        if (value == null) return "";
        if (value.length() == 0) return "\"\"";

        boolean needQuoting = value.startsWith(" ") || value.endsWith(" ") || (value.startsWith("#") && first);
        if (!needQuoting) {
            for (char ch : new char[]{'\"', '\\', '\r', '\n', '\t', delimiter}) {
                if (value.indexOf(ch) != -1) {
                    needQuoting = true;
                    break;
                }
            }
        }

        String result = value.replace("\"", "\"\"");
        if (needQuoting) result = "\"" + result + "\"";
        return result;
    }

    public byte[] getBytes(){
        return stream.toByteArray();
    }
}
