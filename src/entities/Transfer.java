package entities;

import java.sql.Time;

import javax.persistence.*;

@Entity
@Table(name = "transfer")
public class Transfer {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Employee getTransferer() {
        return transferer;
    }

    public void setTransferer(Employee transferer) {
        this.transferer = transferer;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @Column
    String time;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    Employee transferer;
    @ManyToOne
    @JoinColumn(name = "company_id")
    Company company;
}
