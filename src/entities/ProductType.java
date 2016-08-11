package entities;

import javax.persistence.*;

@Entity
@Table(name = "product_type")
public class ProductType {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "full_model_name")
    String fullModelName;
    @Column
    Long barcode;
    @Column(length = 64)
    String producer;
    @Column(length = 64)
    String model;
    @Column
    Integer weight;
    @Column(name = "pack_width")
    Integer packWidth;
    @Column(name= "pack_height")
    Integer packHeight;
    @Column(name="pack_lenght")
    Integer packLength;
    @ManyToOne
    @JoinColumn(name = "unit_set_id")
    UnitSet unitSet;

    public String getFullModelName() {
        return fullModelName;
    }
    public void setFullModelName(String fullModelName) {
        this.fullModelName = fullModelName;
    }
    public Long getBarcode() {
        return barcode;
    }
    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }
    public String getProducer() {
        return producer;
    }
    public void setProducer(String producer) {
        this.producer = producer;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public Integer getWeight() {
        return weight;
    }
    public void setWeight(Integer weight) {
        this.weight = weight;
    }
    public Integer getPackWidth() {
        return packWidth;
    }
    public void setPackWidth(Integer packWidth) {
        this.packWidth = packWidth;
    }
    public Integer getPackHeight() {
        return packHeight;
    }
    public void setPackHeight(Integer packHeight) {
        this.packHeight = packHeight;
    }
    public Integer getPackLength() {
        return packLength;
    }
    public void setPackLength(Integer packLength) {
        this.packLength = packLength;
    }
    public UnitSet getUnitSet() {
        return unitSet;
    }
    public void setUnitSet(UnitSet unitSet) {
        this.unitSet = unitSet;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
}
