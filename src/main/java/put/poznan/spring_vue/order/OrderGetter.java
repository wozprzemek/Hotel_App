package put.poznan.spring_vue.order;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class OrderGetter {
    private int orderID;
    private float totalPrice;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm")
    private Date timeOfOrder;

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getTimeOfOrder() {
        return timeOfOrder;
    }

    public void setTimeOfOrder(Date timeOfOrder) {
        this.timeOfOrder = timeOfOrder;
    }
}
