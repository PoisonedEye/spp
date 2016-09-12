package actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import util.CsvWriter;
import util.DocumentHelper;
import util.ServiceUtil;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class CsvReportAction extends ActionSupport implements SessionAware {
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

    public String transfers()throws IOException {
        if (ServiceUtil.getLoginService().isLogined(userSession)) {
            if (userSession.get("position").equals("Manager")){
                try(CsvWriter writer = new CsvWriter()){
                    inputStream = new ByteArrayInputStream(DocumentHelper.generateTransfer(writer));
                    return SUCCESS;
                }
            }
        }
        return "denied";
    }

    public String receivings()throws IOException{
        if (ServiceUtil.getLoginService().isLogined(userSession)) {
            if (userSession.get("position").equals("Manager")){
                try(CsvWriter writer = new CsvWriter()){
                inputStream = new ByteArrayInputStream(DocumentHelper.generateReceiving(writer));
                return SUCCESS;
                }
            }
        }
        return "denied";
    }

    public String acceptors()throws IOException{
        if (ServiceUtil.getLoginService().isLogined(userSession)) {
            if (userSession.get("position").equals("Manager")){
                try(CsvWriter writer = new CsvWriter()) {

                    inputStream = new ByteArrayInputStream(DocumentHelper.generateAcceptors(writer));
                    return SUCCESS;
                }
            }
        }
        return "denied";
    }

    public String shifts()throws IOException{
        if (ServiceUtil.getLoginService().isLogined(userSession)) {
            if (userSession.get("position").equals("Manager")){
                try(CsvWriter writer = new CsvWriter()) {
                    inputStream = new ByteArrayInputStream(DocumentHelper.generateAcceptorShifts(writer));
                    return SUCCESS;
                }
            }
        }
        return "denied";
    }

    public String cells()throws IOException{
        if (ServiceUtil.getLoginService().isLogined(userSession)) {
            if (userSession.get("position").equals("Manager")){
                try(CsvWriter writer = new CsvWriter()) {
                    inputStream = new ByteArrayInputStream(DocumentHelper.generateCells(writer));
                    return SUCCESS;
                }
            }
        }
        return "denied";
    }

}
