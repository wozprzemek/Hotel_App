package put.poznan.spring_vue.productInOrder;

import java.util.List;

public class ProductInOrderDetails {
    private int orderID;
    private List<ProductInProductInOrderDetails> products;

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public List<ProductInProductInOrderDetails> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInProductInOrderDetails> products) {
        this.products = products;
    }
}
