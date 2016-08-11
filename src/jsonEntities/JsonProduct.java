package jsonEntities;

public class JsonProduct extends JsonBase{
    String recieving;
    String transfer;
    String firstGetTime;
    String lastGetTime;
    String productType;

    public String getRecieving() {
        return recieving;
    }
    public void setRecieving(String recieving) {
        this.recieving = recieving;
    }
    public String getTransfer() {
        return transfer;
    }
    public void setTransfer(String transfer) {
        this.transfer = transfer;
    }
    public String getFirstGetTime() {
        return firstGetTime;
    }
    public void setFirstGetTime(String firstGetTime) {
        this.firstGetTime = firstGetTime;
    }
    public String getLastGetTime() {
        return lastGetTime;
    }
    public void setLastGetTime(String lastGetTime) {
        this.lastGetTime = lastGetTime;
    }
    public String getProductType() {
        return productType;
    }
    public void setProductType(String productType) {
        this.productType = productType;
    }


}
