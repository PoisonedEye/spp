package jsonEntities;

public class JsonCellVisiting extends JsonBase{
    String shift;
    String product;
    String time;
    Boolean isProductTaken;
    String cell;

    public String getShift() {
        return shift;
    }
    public void setShift(String shift) {
        this.shift = shift;
    }
    public String getProduct() {
        return product;
    }
    public void setProduct(String product) {
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
    public String getCell() {
        return cell;
    }
    public void setCell(String cell) {
        this.cell = cell;
    }
}
