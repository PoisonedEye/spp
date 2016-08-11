package jsonEntities;

public class JsonCellType extends JsonBase{
    String width;
    String height;
    String depth;
    String maxWeight;
    String unitSet;

    public String getWidth() {
        return width;
    }
    public void setWidth(String width) {
        this.width = width;
    }
    public String getHeight() {
        return height;
    }
    public void setHeight(String height) {
        this.height = height;
    }
    public String getDepth() {
        return depth;
    }
    public void setDepth(String depth) {
        this.depth = depth;
    }
    public String getMaxWeight() {
        return maxWeight;
    }
    public void setMaxWeight(String maxWeight) {
        this.maxWeight = maxWeight;
    }
    public String getUnitSet() {
        return unitSet;
    }
    public void setUnitSet(String unitSet) {
        this.unitSet = unitSet;
    }
}
