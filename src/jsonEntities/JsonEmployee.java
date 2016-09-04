package jsonEntities;

public class JsonEmployee extends JsonBase{
    String tin;
    String fullName;
    String takingOffice;
    String phoneNumber;
    String position;
    Boolean fired;
    String login;
    String password;

    public String getTin() {
        return tin;
    }
    public void setTin(String tin) {
        this.tin = tin;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getTakingOffice() {
        return takingOffice;
    }
    public void setTakingOffice(String takingOffice) {
        this.takingOffice = takingOffice;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public Boolean getFired() {
        return fired;
    }
    public void setFired(Boolean fired) {
        this.fired = fired;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
