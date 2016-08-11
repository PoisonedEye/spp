package jsonEntities;

public class JsonCell extends JsonBase{
    String number;
    String cellType;
    String x;
    String y;

    public String getY() {
        return y;
    }
    public void setY(String y) {
        this.y = y;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getCellType() {
        return cellType;
    }
    public void setCellType(String cellType) {
        this.cellType = cellType;
    }
    public String getX() {
        return x;
    }
    public void setX(String x) {
        this.x = x;
    }
}
