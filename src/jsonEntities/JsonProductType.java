package jsonEntities;

public class JsonProductType extends JsonBase{
    String fullModelName;
    String barcode;
    String producer;
    String model;
    String weight;
    String packWidth;
    String packHeight;
    String packLength;
    String unitSet;

    public String getUnitSet() {
        return unitSet;
    }
    public void setUnitSet(String unitSet) {
        this.unitSet = unitSet;
    }
    public String getFullModelName() {
        return fullModelName;
    }
    public void setFullModelName(String fullModelName) {
        this.fullModelName = fullModelName;
    }
    public String getBarcode() {
        return barcode;
    }
    public void setBarcode(String barcode) {
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
    public String getWeight() {
        return weight;
    }
    public void setWeight(String weight) {
        this.weight = weight;
    }
    public String getPackWidth() {
        return packWidth;
    }
    public void setPackWidth(String packWidth) {
        this.packWidth = packWidth;
    }
    public String getPackHeight() {
        return packHeight;
    }
    public void setPackHeight(String packHeight) {
        this.packHeight = packHeight;
    }
    public String getPackLength() {
        return packLength;
    }
    public void setPackLength(String packLength) {
        this.packLength = packLength;
    }
}
