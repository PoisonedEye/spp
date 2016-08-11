package jsonEntities;

public class JsonRecieving extends JsonBase{
    String time;
    String reciever;
    String company;

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getReciever() {
        return reciever;
    }
    public void setReciever(String reciever) {
        this.reciever = reciever;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
}
