package entities;

import javax.persistence.*;

@Entity
@Table(name = "cell_type")
public class CellType {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @Column
    Integer width;
    @Column
    Integer height;
    @Column
    Integer depth;
    @Column(name="max_weight")
    Integer maxWeight;
    @ManyToOne
    @JoinColumn(name = "unit_set_id")
    UnitSet unitSet;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public Integer getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(Integer maxWeight) {
        this.maxWeight = maxWeight;
    }

    public UnitSet getUnitSet() {
        return unitSet;
    }

    public void setUnitSet(UnitSet unitSet) {
        this.unitSet = unitSet;
    }


}
