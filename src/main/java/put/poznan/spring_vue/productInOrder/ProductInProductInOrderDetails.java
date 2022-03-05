package put.poznan.spring_vue.productInOrder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ProductInProductInOrderDetails {
    private String category;
    private String productName;
    private int productQuantity;
    private Date serviceTime;
    private float subtotalPrice;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Date getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(Date serviceTime) throws ParseException {
        this.serviceTime = serviceTime;
    }

    public float getSubtotalPrice() {
        return subtotalPrice;
    }

    public void setSubtotalPrice(float subtotalPrice) {
        this.subtotalPrice = subtotalPrice;
    }
}
