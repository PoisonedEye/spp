package entities;

import javax.persistence.*;

@Entity
@Table(name = "acceptor_shift")
public class AcceptorShift {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public Employee getAcceptor() {
        return acceptor;
    }

    public void setAcceptor(Employee acceptor) {
        this.acceptor = acceptor;
    }

    public String getBegining() {
        return begining;
    }

    public void setBegining(String begining) {
        this.begining = begining;
    }

    public String getEnding() {
        return ending;
    }

    public void setEnding(String ending) {
        this.ending = ending;
    }

    @ManyToOne
    @JoinColumn(name = "acceptor_id")
    private Employee acceptor;
    @Column
    private String begining;
    @Column
    private String ending;
}
