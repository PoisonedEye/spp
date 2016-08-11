package entities;

import java.sql.Time;

import javax.persistence.*;

@Entity
@Table(name = "recieving")
public class Recieving {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Employee getReciever() {
        return reciever;
    }

    public void setReciever(Employee reciever) {
        this.reciever = reciever;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Column
    String time;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    Employee reciever;
    @ManyToOne
    @JoinColumn(name = "company_id")
    Company company;
}
