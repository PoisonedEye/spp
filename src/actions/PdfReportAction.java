package actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

import java.io.*;
import java.util.Map;

public class PdfReportAction extends ActionSupport implements SessionAware {
    private Map<String, Object> userSession;

    public void setSession(Map<String, Object> session) {
        userSession = session;
    }
    private String login;

    private InputStream inputStream;
    public InputStream getInputStream() {
        return inputStream;
    }
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String transfers(){
        return SUCCESS;
    }

    public String receivings(){
        return SUCCESS;
    }

    public String work(){
        return SUCCESS;
    }

    public String products(){
        return SUCCESS;
    }

    public String acceptors(){
        return SUCCESS;
    }
}
