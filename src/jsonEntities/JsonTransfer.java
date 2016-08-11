package jsonEntities;

public class JsonTransfer extends JsonBase{
    String time;
    String transferer;
    String Company;

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getTransferer() {
        return transferer;
    }
    public void setTransferer(String transferer) {
        this.transferer = transferer;
    }
    public String getCompany() {
        return Company;
    }
    public void setCompany(String company) {
        Company = company;
    }

}
