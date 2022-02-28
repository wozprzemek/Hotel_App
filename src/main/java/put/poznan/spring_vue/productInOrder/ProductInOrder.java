package put.poznan.spring_vue.productInOrder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import put.poznan.spring_vue.guest.Guest;
import put.poznan.spring_vue.order.Order;
import put.poznan.spring_vue.product.Product;
import put.poznan.spring_vue.reservation.Reservation;
import put.poznan.spring_vue.hotel.Hotel;
import put.poznan.spring_vue.room.Room;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "PRODUCT_IN_ORDER")
public class ProductInOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_IN_ORDER_ID")
    private int id;

    @Column(name = "PRODUCT_QUANTITY")
    private int productQuantity;

    @Column(name = "PRODUCT_SUBTOTAL_PRICE")
    private float productSubtotalPrice;

    @Column(name = "SERVICE_TIME")
    private Date serviceTime;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="ORDER_ID")
    private Order order;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="RESERVATION_ID")
    private Reservation reservation;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="GUEST_ID")
    private Guest guest;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="PRODUCT_ID")
    private Product product;

    public ProductInOrder(int productQuantity, float productSubtotalPrice, Date serviceTime, Order order, Reservation reservation, Guest guest, Product product) {
        this.productQuantity = productQuantity;
        this.productSubtotalPrice = productSubtotalPrice;
        this.serviceTime = serviceTime;
        this.order = order;
        this.reservation = reservation;
        this.guest = guest;
        this.product = product;
    }

    public ProductInOrder() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public float getProductSubtotalPrice() {
        return productSubtotalPrice;
    }

    public void setProductSubtotalPrice(float productSubtotalPrice) {
        this.productSubtotalPrice = productSubtotalPrice;
    }

    public Date getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(Date serviceTime) {
        this.serviceTime = serviceTime;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
