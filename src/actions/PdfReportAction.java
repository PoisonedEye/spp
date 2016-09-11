package actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import services.LoginService;
import util.Csv;
import util.ServiceUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
        Csv.Writer writer = new Csv.Writer(new ByteArrayOutputStream());

        writer.value("ololo");
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
