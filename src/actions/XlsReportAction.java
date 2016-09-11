package actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import util.CsvWriter;
import util.DocumentHelper;
import util.ServiceUtil;
import util.XlsWriter;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

public class XlsReportAction extends ActionSupport implements SessionAware {
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
        if (ServiceUtil.getLoginService().isLogined(userSession)) {
            if (userSession.get("position").equals("Manager")){
                try(XlsWriter writer = new XlsWriter("Transfers")){
                    inputStream = new ByteArrayInputStream(DocumentHelper.generateTransfer(writer));
                    return SUCCESS;
                }
            }
        }
        return "denied";
    }

    public String receivings(){
        if (ServiceUtil.getLoginService().isLogined(userSession)) {
            if (userSession.get("position").equals("Manager")){
                try(XlsWriter writer = new XlsWriter("Receivings")){
                    inputStream = new ByteArrayInputStream(DocumentHelper.generateReceiving(writer));
                    return SUCCESS;
                }
            }
        }
        return "denied";
    }

    public String acceptors(){
        if (ServiceUtil.getLoginService().isLogined(userSession)) {
            if (userSession.get("position").equals("Manager")){
                try(XlsWriter writer = new XlsWriter("Acceptors")){
                    inputStream = new ByteArrayInputStream(DocumentHelper.generateAcceptors(writer));
                    return SUCCESS;
                }
            }
        }
        return "denied";
    }

    public String shifts(){
        if (ServiceUtil.getLoginService().isLogined(userSession)) {
            if (userSession.get("position").equals("Manager")){
                try(XlsWriter writer = new XlsWriter("Acceptor shifts")){
                    inputStream = new ByteArrayInputStream(DocumentHelper.generateAcceptorShifts(writer));
                    return SUCCESS;
                }
            }
        }
        return "denied";
    }

    public String cells(){
        if (ServiceUtil.getLoginService().isLogined(userSession)) {
            if (userSession.get("position").equals("Manager")){
                try(XlsWriter writer = new XlsWriter("Cells")){
                    inputStream = new ByteArrayInputStream(DocumentHelper.generateCells(writer));
                    return SUCCESS;
                }
            }
        }
        return "denied";
    }
}
