package entities;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column(name = "unical_id")
    Integer unicalId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUnicalId() {
        return unicalId;
    }

    public void setUnicalId(Integer unicalId) {
        this.unicalId = unicalId;
    }

    public Recieving getRecieving() {
        return recieving;
    }

    public void setRecieving(Recieving recieving) {
        this.recieving = recieving;
    }

    public Transfer getTransfer() {
        return transfer;
    }

    public void setTransfer(Transfer transfer) {
        this.transfer = transfer;
    }

    public String getFirstGetTime() {
        return firstGetTime;
    }

    public void setFirstGetTime(String firstGetTIme) {
        this.firstGetTime = firstGetTIme;
    }

    public String getLastGetTime() {
        return lastGetTime;
    }

    public void setLastGetTime(String lastGetTime) {
        this.lastGetTime = lastGetTime;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    @ManyToOne
    @JoinColumn(name = "recieving_id")
    Recieving recieving;
    @ManyToOne
    @JoinColumn(name = "transfer_id")
    Transfer transfer;
    @Column(name = "first_get_time")
    String firstGetTime;
    @Column(name = "last_get_time")
    String lastGetTime;
    @ManyToOne
    @JoinColumn(name = "product_type_id")
    ProductType productType;
}
