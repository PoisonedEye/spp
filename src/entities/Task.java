package entities;

import javax.persistence.*;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @ManyToOne
    @JoinColumn(name = "acceptor_shift_id")
    AcceptorShift acceptorShift;
    @ManyToOne
    @JoinColumn(name = "product_type_id")
    ProductType productType;
    @Column
    Integer count;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public AcceptorShift getAcceptorShift() {
        return acceptorShift;
    }
    public void setAcceptorShift(AcceptorShift acceptorShift) {
        this.acceptorShift = acceptorShift;
    }
    public ProductType getProductType() {
        return productType;
    }
    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
}
