package entities;

import java.sql.Time;

import javax.persistence.*;

@Entity
@Table(name = "cell_visiting")
public class CellVisiting {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AcceptorShift getShift() {
        return shift;
    }

    public void setShift(AcceptorShift shift) {
        this.shift = shift;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getProductTaken() {
        return isProductTaken;
    }

    public void setProductTaken(Boolean productTaken) {
        isProductTaken = productTaken;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    @ManyToOne
    @JoinColumn(name = "shift_id")
    AcceptorShift shift;
    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;
    @Column
    String time;
    @Column(name="is_product_taken")
    Boolean isProductTaken;
    @ManyToOne
    @JoinColumn(name = "cell_id")
    Cell cell;
}
