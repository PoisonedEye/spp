package entities;

import javax.persistence.*;

@Entity
@Table(name = "cell")
public class Cell {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @Column(name = "number", length = 8)
    String number;
    @ManyToOne
    @JoinColumn(name = "cell_type_id")
    CellType cellType;
    @Column
    Float x;
    @Column
    Float y;

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public CellType getCellType() {
        return cellType;
    }
    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }
    public Float getX() {
        return x;
    }
    public void setX(Float x) {
        this.x = x;
    }
    public Float getY() {
        return y;
    }
    public void setY(Float y) {
        this.y = y;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
}
