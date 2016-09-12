package util;

import java.io.IOException;

public abstract class DocumentWriter implements AutoCloseable{
    public abstract DocumentWriter t(String value);//TITLE
    public abstract DocumentWriter h(String value);//HEADER
    public abstract DocumentWriter v(String value);//VALUE
    public abstract DocumentWriter newLine();
    public abstract DocumentWriter flush();
    public abstract byte[] getBytes()throws IOException;
}
