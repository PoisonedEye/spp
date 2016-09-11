package jsonEntities;

public class JsonNewReceiving{
    JsonProductType[] products;
    Integer[] ids;
    Integer[] counts;

    public Integer[] getIds() {
        return ids;
    }
    public void setIds(Integer[] ids) {
        this.ids = ids;
    }
    public JsonProductType[] getProducts() {
        return products;
    }
    public void setProducts(JsonProductType[] products) {
        this.products = products;
    }
    public Integer[] getCounts() {
        return counts;
    }
    public void setCounts(Integer[] counts) {
        this.counts = counts;
    }
}
