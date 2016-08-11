package entities;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @Column(name = "tin")
    Long tin;
    @Column(name = "full_name", length = 64)
    String fullName;
    @Column(name = "taking_office")
    String takingOffice;
    @Column(name = "phone_number", length = 20)
    String phoneNumber;
    @ManyToOne
    @JoinColumn(name = "position")
    Position position;
    @Column
    Boolean fired;
    @Column(length = 32)
    String login;
    @Column(length = 32)
    String password;

    public Long getTin() {
        return tin;
    }
    public void setTin(Integer tin) {
        this.id = tin;
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
    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
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
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setTin(Long tin) {
        this.tin = tin;
    }
}
